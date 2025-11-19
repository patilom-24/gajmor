package com.gajmor.Gajmore.Services.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class CareerMailService {

    public String buildCareerAdminEmail(String name, String qualification, int age, String email,
                                        String position, String exp1, String exp2) {

        String logoUrl = "https://i.ibb.co/LzVYZnpV/Gajmor-Logo.png";
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset='UTF-8'>");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }");
        sb.append(".navbar { background-color: #1a1a2e; padding: 20px; text-align: center; }");
        sb.append(".navbar img { max-height: 100px; }");
        sb.append(".email-container { max-width: 650px; margin: 20px auto; background: #ffffff; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        sb.append(".title { font-size: 24px; color: #1a1a2e; text-align: center; font-weight: bold; margin-bottom: 25px; }");
        sb.append(".label { font-weight: bold; color: #1a1a2e; }");
        sb.append(".value { color: #333333; }");
        sb.append(".info-box { background-color: #f1f1f1; padding: 15px; border-radius: 8px; margin-top: 10px; line-height: 1.6; }");
        sb.append(".footer { background-color: #1a1a2e; color: #ffffff; text-align: center; padding: 20px; font-size: 14px; border-radius: 0 0 12px 12px; }");
        sb.append("</style></head><body>");

        // Header
        sb.append("<div class='navbar'><img src='").append(logoUrl).append("' alt='Gajmor Logo' /></div>");

        // Content
        sb.append("<div class='email-container'>");
        sb.append("<div class='title'>New Career Application - Gajmor Design</div>");
        sb.append("<p><span class='label'>Full Name:</span> <span class='value'>").append(name).append("</span></p>");
        sb.append("<p><span class='label'>Qualification:</span> <span class='value'>").append(qualification).append("</span></p>");
        sb.append("<p><span class='label'>Age:</span> <span class='value'>").append(age).append("</span></p>");
        sb.append("<p><span class='label'>Email ID:</span> <span class='value'>").append(email).append("</span></p>");
        sb.append("<p><span class='label'>Applied Position:</span> <span class='value'>").append(position).append("</span></p>");
        sb.append("<p><span class='label'>Work Experience:</span></p>");
        sb.append("<div class='info-box'>").append(exp1).append("</div>");
        sb.append("<p><span class='label'>Why Gajmor:</span></p>");
        sb.append("<div class='info-box'>").append(exp2).append("</div>");
        sb.append("<p><b>Resume attached with this email.</b></p>");
        sb.append("</div>");

        // Footer
        sb.append("<div class='footer'>This application was submitted via <strong>Gajmor Careers</strong></div>");
        sb.append("</body></html>");

        return sb.toString();
    }

    public String buildCareerApplicantEmail(String name) {
        String logoUrl = "https://i.ibb.co/LzVYZnpV/Gajmor-Logo.png";
        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset='UTF-8'>");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color: #f6f9fc; margin: 0; padding: 0; }");
        sb.append(".container { max-width: 600px; margin: 50px auto; background: #ffffff; border-radius: 8px; padding: 30px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); }");
        sb.append(".header { text-align: center; padding-bottom: 20px; }");
        sb.append(".logo { max-width: 150px; margin-bottom: 10px; }");
        sb.append(".title { font-size: 24px; color: #333333; margin-top: 10px; font-weight: 600; }");
        sb.append(".content { font-size: 16px; color: #444444; line-height: 1.6; padding-top: 10px; }");
        sb.append(".highlight { color: #ffae00; font-weight: 500; }");
        sb.append(".footer { margin-top: 30px; font-size: 13px; color: #888888; text-align: center; border-top: 1px solid #eee; padding-top: 20px; }");
        sb.append("</style></head><body>");

        sb.append("<div class='container'>");
        sb.append("<div class='header'><img src='").append(logoUrl).append("' alt='Gajmor Logo' class='logo' />");
        sb.append("<div class='title'>Thank You for Applying!</div></div>");

        sb.append("<div class='content'>");
        sb.append("<p>Dear <span class='highlight'>").append(name).append("</span>,</p>");
        sb.append("<p>We appreciate your interest in joining <strong>Gajmor Design</strong>. Your application has been successfully received.</p>");
        sb.append("<p>Our HR team will carefully review your profile and reach out if your skills and experience match our current openings.</p>");
        sb.append("<p>We aim to respond within <strong>5-7 working days</strong>. Thank you for your patience.</p>");
        sb.append("<p>Warm regards,<br><strong>Gajmor HR Team</strong></p>");
        sb.append("</div>");

        sb.append("<div class='footer'>This is an automated confirmation from Gajmor Careers Portal</div>");
        sb.append("</div></body></html>");

        return sb.toString();
    }
}
