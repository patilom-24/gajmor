package com.gajmor.Gajmore.Controllers;


import com.gajmor.Gajmore.Model.Enquiry;
import com.gajmor.Gajmore.Repository.EnquiryRepository;
import com.gajmor.Gajmore.Services.ServiceImpl.EmailService;
import com.gajmor.Gajmore.Services.ServiceImpl.EnquiryMailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SubmitRequest {

    @Autowired
    EmailService emailService;

    @Autowired
    EnquiryMailTemplate enquiryMailTemplate;

    @Autowired
    EnquiryRepository enquiryRepository;

    @Value("${email.from}")
    private String fromAddress;

    @PostMapping("/submitEnquiry")
    private String getEnquiryDetails(@ModelAttribute Enquiry enquiry, RedirectAttributes redirectAttributes){
        enquiry.setEnquiryFor(enquiryMailTemplate.formatEnquiryValue(enquiry.getEnquiryFor()));
        String enquiryFor = enquiryMailTemplate.formatEnquiryValue(enquiry.getEnquiryFor());
        System.out.println(enquiry.getName() +" "+enquiry.getEmail()+" "+enquiry.getMobileNo()+" "+enquiryFor);
        enquiryRepository.save(enquiry);

//        For send the response email to the client
        try {
            // Send Email To the Client
            String html = enquiryMailTemplate.buildClientEnquiryResponseEmail(enquiry.getName());
            emailService.sendEmail(enquiry.getEmail(), "Thank You for Your Enquiry - Gajmor", html);

            // Sent Email To The Admin
            String emailBody = enquiryMailTemplate.buildEnquiryEmail(enquiry.getName(),enquiry.getEmail(),enquiry.getMobileNo(),enquiry.getEnquiryFor());
            emailService.sendEmail(fromAddress,"Enquiry For the "+ enquiryMailTemplate.formatEnquiryValue(enquiry.getEnquiryFor()),emailBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("successMessage", "We will connect with you within 2 to 3 days. Thank you!");
        return "redirect:/?success=true";
    }

}
