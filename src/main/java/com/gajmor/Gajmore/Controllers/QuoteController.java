package com.gajmor.Gajmore.Controllers;

import com.gajmor.Gajmore.Model.QuoteRequest;
import com.gajmor.Gajmore.Repository.QuoteRepository;
import com.gajmor.Gajmore.Services.ServiceImpl.EmailService;
import com.gajmor.Gajmore.Services.ServiceImpl.EnquiryMailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class QuoteController {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EnquiryMailTemplate enquiryMailTemplate;

    @Value("${mail.toInterior}")
    String mailToInterior;

    @PostMapping("/getQuote")
    public String getQuote(
            @ModelAttribute QuoteRequest quote,
            RedirectAttributes redirectAttributes) {

        // ✅ Server-side mobile validation
        if (!quote.getMobile().matches("^[6-9]\\d{9}$")) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Please enter a valid 10-digit mobile number"
            );
            return "redirect:/";
        }

        // 🔥 TODO: Save to DB / Send Email / WhatsApp
        System.out.println("Quote Request Received:");
        System.out.println("Name: " + quote.getName());
        System.out.println("Email: " + quote.getEmail());
        System.out.println("Mobile: " + quote.getMobile());

        String htmlTemplet = enquiryMailTemplate.buildQuoteConfirmationEmail(quote.getName());
        emailService.sendEmail(quote.getEmail(),"Thank You for Contacting Gajmor Interiors",htmlTemplet);

        String htmlTemplet2 = enquiryMailTemplate.buildAdminQuoteEmail(quote.getName(),quote.getEmail(),quote.getMobile());
        emailService.sendEmail(mailToInterior,"New Enquiry Received – Gajmor Website",htmlTemplet2);

        quoteRepository.save(quote);
        System.out.println("Quote request save ");

        // ✅ Success message
        redirectAttributes.addFlashAttribute(
                "success",
                "Thank you! Our designer will contact you shortly."
        );

        return "redirect:/";
    }
}
