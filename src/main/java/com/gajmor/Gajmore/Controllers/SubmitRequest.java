package com.gajmor.Gajmore.Controllers;


import com.gajmor.Gajmore.Model.Enquiry;
import com.gajmor.Gajmore.Repository.EnquiryRepository;
import com.gajmor.Gajmore.Services.ServiceImpl.EmailService;
import com.gajmor.Gajmore.Services.ServiceImpl.EnquiryMailTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    @ResponseBody
    public ResponseEntity<?> submitEnquiry(@ModelAttribute Enquiry enquiry) {

        enquiry.setEnquiryFor(enquiryMailTemplate.formatEnquiryValue(enquiry.getEnquiryFor()));
        enquiryRepository.save(enquiry);

        try {
            // Email to client
            String html = enquiryMailTemplate.buildClientEnquiryResponseEmail(enquiry.getName());
            emailService.sendEmail(enquiry.getEmail(), "Thank You for Your Enquiry - Gajmor", html);

            // Email to admin
            String emailBody = enquiryMailTemplate.buildEnquiryEmail(
                    enquiry.getName(),
                    enquiry.getEmail(),
                    enquiry.getMobileNo(),
                    enquiry.getEnquiryFor()
            );
            emailService.sendEmail(fromAddress, "New Enquiry - Gajmor", emailBody);

            return ResponseEntity.ok().build();   // 🔥 return 200 to fetch
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(500).body("Email failed");
        }
    }


}
