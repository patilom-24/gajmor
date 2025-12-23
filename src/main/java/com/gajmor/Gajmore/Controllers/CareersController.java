package com.gajmor.Gajmore.Controllers;

import com.gajmor.Gajmore.Services.ServiceImpl.CareerMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class CareersController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private CareerMailService careersMail;

   /* @GetMapping("/careers")
    public String showCareersForm() {
        return "careers";
    }*/

    @Value("${mail.toHr}")
    private String mailToHr;

    @Autowired
    private HttpSession session;

    @PostMapping("/careers/apply")
    public String submitCareerApplication(
            @RequestParam String name,
            @RequestParam String qualification,
            @RequestParam int age,
            @RequestParam String email,
            @RequestParam String position,
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("experience") String exp1,
            Model model,
            RedirectAttributes redirectAttributes
    ) throws MessagingException, IOException {



        // ✅ Build emails
        String adminHtml = careersMail.buildCareerAdminEmail(name, qualification, age, email, position, exp1);
        String applicantHtml = careersMail.buildCareerApplicantEmail(name);

        // ✅ Send Admin Email with Resume Attachment
        MimeMessage adminMessage = mailSender.createMimeMessage();
        MimeMessageHelper adminHelper = new MimeMessageHelper(adminMessage, true);
        adminHelper.setTo(mailToHr); // change to your HR email
        adminHelper.setSubject("New Career Application - " + name);
        adminHelper.setText(adminHtml, true);
        adminHelper.addAttachment(resume.getOriginalFilename(), (InputStreamSource) resume::getInputStream);
        mailSender.send(adminMessage);

        // ✅ Send Applicant Email
        MimeMessage applicantMessage = mailSender.createMimeMessage();
        MimeMessageHelper applicantHelper = new MimeMessageHelper(applicantMessage, true);
        applicantHelper.setTo(email);
        applicantHelper.setSubject("Gajmor Careers - Application Received");
        applicantHelper.setText(applicantHtml, true);
        mailSender.send(applicantMessage);

        // ✅ Pass success message for only the next request
        redirectAttributes.addFlashAttribute("success", true);
        redirectAttributes.addFlashAttribute("applicantName", name);

        // ✅ Redirect to avoid form resubmission + prevent modal on refresh
        return "redirect:/jobs";// same page reload with animation
    }

    @GetMapping("/careers")
    public String showCareersPage(HttpSession session, Model model) {
        Boolean emailSent = (Boolean) session.getAttribute("emailSent");
        if (emailSent != null && emailSent) {
            model.addAttribute("success", true);
            session.removeAttribute("emailSent"); // ✅ prevents showing again
        }
        return "jobs";
    }

}
