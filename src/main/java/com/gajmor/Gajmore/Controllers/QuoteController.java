package com.gajmor.Gajmore.Controllers;

import com.gajmor.Gajmore.Model.QuoteRequest;
import com.gajmor.Gajmore.Repository.QuoteRepository;
import com.gajmor.Gajmore.Services.ServiceImpl.EmailService;
import com.gajmor.Gajmore.Services.ServiceImpl.EnquiryMailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @ResponseBody
    public ResponseEntity<?> getQuote(@RequestBody QuoteRequest quote) {

        // NULL + REGEX SAFE VALIDATION
        if (quote.getMobile() == null ||
                !quote.getMobile().matches("^[6-9]\\d{9}$")) {
            return ResponseEntity.badRequest()
                    .body("Please enter a valid 10-digit mobile number");
        }

        System.out.println("Quote Request Received:");
        System.out.println("Name: " + quote.getName());
        System.out.println("Email: " + quote.getEmail());
        System.out.println("Mobile: " + quote.getMobile());

        String userTemplate =
                enquiryMailTemplate.buildQuoteConfirmationEmail(quote.getName());
        emailService.sendEmail(
                quote.getEmail(),
                "Thank You for Contacting Gajmor Interiors",
                userTemplate
        );

        String adminTemplate =
                enquiryMailTemplate.buildAdminQuoteEmail(
                        quote.getName(),
                        quote.getEmail(),
                        quote.getMobile()
                );
        emailService.sendEmail(
                mailToInterior,
                "New Enquiry Received – Gajmor Website",
                adminTemplate
        );

        quoteRepository.save(quote);

        return ResponseEntity.ok(
                "Thank you! Our designer will contact you shortly."
        );
    }
}
