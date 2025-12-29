package com.gajmor.Gajmore.Services.ServiceImpl;

import org.springframework.stereotype.Service;

@Service
public class EnquiryMailTemplate {
    public String buildEnquiryEmail(String name, String email, String phone, String message) {
        String logoUrl = "https://res.cloudinary.com/dbakcsfdz/image/upload/v1766310035/GajmorLogo_eubrc1.png";

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset=\"UTF-8\">");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }");
        sb.append(".navbar { background-color: #1a1a2e; padding: 20px; text-align: center; }");
        sb.append(".navbar img { max-height: 100px; }");
        sb.append(".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        sb.append(".title { font-size: 24px; color: #1a1a2e; text-align: center; font-weight: bold; margin-bottom: 25px; }");
        sb.append(".content { font-size: 16px; color: #333333; line-height: 1.7; }");
        sb.append(".label { font-weight: bold; color: #1a1a2e; }");
        sb.append(".enquiry-box { background-color: #f1f1f1; padding: 15px; border-radius: 8px; margin-top: 10px; }");
        sb.append(".footer { background-color: #1a1a2e; color: #ffffff; text-align: center; padding: 20px; font-size: 14px; border-radius: 0 0 12px 12px; }");
        sb.append("a { color: #f8d90f; text-decoration: none; }");
        sb.append("</style></head><body>");

        // Navigation / Logo Bar
        sb.append("<div class=\"navbar\">");
        sb.append("<img src=\"").append(logoUrl).append("\" alt=\"Gajmor Logo\" />");
        sb.append("</div>");

        // Main email body
        sb.append("<div class=\"email-container\">");
        sb.append("<div class=\"title\">New Enquiry - Gajmor Website</div>");
        sb.append("<div class=\"content\">");

        sb.append("<p><span class=\"label\">Customer Name:</span> ").append(name).append("</p>");
        sb.append("<p><span class=\"label\">Customer Email ID:</span> ").append(email).append("</p>");
        sb.append("<p><span class=\"label\">Customer Mobile Number:</span> ").append(phone).append("</p>");
        sb.append("<p><span class=\"label\">Enquiry For:</span></p>");
        sb.append("<div class=\"enquiry-box\">").append(formatEnquiryValue(message)).append("</div>");

        sb.append("</div>");
        sb.append("</div>");

        // Footer
        sb.append("<div class=\"footer\">");
        sb.append("This enquiry was submitted via <strong>Gajmor</strong> | ");
        sb.append("<a href=\"https://gajmordesign.com\">Visit our website</a>");
        sb.append("<br>© Gajmor Interiors - All rights reserved.");
        sb.append("</div>");

        sb.append("</body></html>");

        return sb.toString();
    }


    public String formatEnquiryValue(String rawValue) {
        if (rawValue == null) return "";
        String cleaned = rawValue.replace("_", " ");
        String[] words = cleaned.split(" ");
        StringBuilder formatted = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formatted.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1).toLowerCase())
                        .append(" ");
            }
        }
        return formatted.toString().trim();
    }


    public String buildClientEnquiryResponseEmail(String clientName) {
        String logoUrl = "https://res.cloudinary.com/dbakcsfdz/image/upload/v1766310035/GajmorLogo_eubrc1.png";

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset=\"UTF-8\">");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color: #f6f9fc; margin: 0; padding: 0; }");
        sb.append(".container { max-width: 600px; margin: 50px auto; background: #ffffff; border-radius: 8px; padding: 30px; box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1); }");
        sb.append(".header { text-align: center; padding-bottom: 20px; }");
        sb.append(".logo { max-width: 150px; margin-bottom: 10px; }");
        sb.append(".title { font-size: 24px; color: #333333; margin-top: 10px; font-weight: 600; }");
        sb.append(".content { font-size: 16px; color: #444444; line-height: 1.6; padding-top: 10px; }");
        sb.append(".highlight { color: #007BFF; font-weight: 500; }");
        sb.append(".footer { margin-top: 30px; font-size: 13px; color: #888888; text-align: center; border-top: 1px solid #eee; padding-top: 20px; }");
        sb.append("</style></head><body>");

        sb.append("<div class=\"container\">");

        // Header with logo and greeting
        sb.append("<div class=\"header\">");
        sb.append("<img src=\"").append(logoUrl).append("\" alt=\"Gajmor Logo\" class=\"logo\" />");
        sb.append("<div class=\"title\">Thank You for Reaching Out!</div>");
        sb.append("</div>");

        // Body content
        sb.append("<div class=\"content\">");
        sb.append("<p>Dear <span class=\"highlight\">").append(clientName).append("</span>,</p>");
        sb.append("<p>Thank you for submitting your enquiry through the <strong>Gajmor</strong> website. We truly appreciate your interest in our interior design services.</p>");
        sb.append("<p>Our team has received your request and will connect with you within <strong>2 to 3 working days</strong> to discuss your requirements in detail.</p>");
        sb.append("<p>If you have any urgent questions, feel free to reply to this email or contact us via phone or WhatsApp.</p>");
        sb.append("<p>Looking forward to helping you create a beautiful space!</p>");
        sb.append("<p>Warm regards,<br><strong>The Gajmor Team</strong></p>");
        sb.append("</div>");

        // Footer
        sb.append("<div class=\"footer\">This is an automated response. Please do not reply directly to this email.</div>");
        sb.append("<a href=\"https://gajmordesign.com\">www.gajmordesign.com</a>");
        sb.append("</div></body></html>");

        return sb.toString();
    }

//    Quote Email Templates Generation

    public String buildQuoteConfirmationEmail(String name) {

        String logoUrl = "https://res.cloudinary.com/dbakcsfdz/image/upload/v1766310035/GajmorLogo_eubrc1.png";

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset=\"UTF-8\">");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color: #f9f9f9; margin: 0; padding: 0; }");
        sb.append(".navbar { background-color: #1a1a2e; padding: 20px; text-align: center; }");
        sb.append(".navbar img { max-height: 100px; }");
        sb.append(".email-container { max-width: 600px; margin: 20px auto; background: #ffffff; border-radius: 12px; padding: 30px; box-shadow: 0 4px 12px rgba(0,0,0,0.1); }");
        sb.append(".title { font-size: 24px; color: #1a1a2e; text-align: center; font-weight: bold; margin-bottom: 25px; }");
        sb.append(".content { font-size: 16px; color: #333333; line-height: 1.7; text-align:center; }");
        sb.append(".cta-btn { display:inline-block; margin-top:20px; padding:12px 26px; background:#e1767e; color:#ffffff; border-radius:30px; font-weight:600; text-decoration:none; }");
        sb.append(".footer { background-color: #1a1a2e; color: #ffffff; text-align: center; padding: 20px; font-size: 14px; border-radius: 0 0 12px 12px; }");
        sb.append("a { color: #f8d90f; text-decoration: none; }");
        sb.append("</style></head><body>");

        // Header
        sb.append("<div class=\"navbar\">");
        sb.append("<img src=\"").append(logoUrl).append("\" alt=\"Gajmor Logo\" />");
        sb.append("</div>");

        // Body
        sb.append("<div class=\"email-container\">");
        sb.append("<div class=\"title\">Thank You for Contacting Gajmor</div>");
        sb.append("<div class=\"content\">");

        sb.append("<p>Dear <strong>").append(name).append("</strong>,</p>");
        sb.append("<p>Thank you for reaching out to <strong>Gajmor Interiors</strong>.</p>");
        sb.append("<p>Your enquiry has been successfully received.</p>");
        sb.append("<p>One of our interior design experts will contact you shortly to understand your requirements better.</p>");

        sb.append("<a href=\"https://gajmor.com\" class=\"cta-btn\">Visit Our Website</a>");

        sb.append("<p style='margin-top:25px;'>We look forward to working with you.</p>");
        sb.append("<p>Warm regards,<br><strong>Team Gajmor</strong></p>");

        sb.append("</div>");
        sb.append("</div>");

        // Footer
        sb.append("<div class=\"footer\">");
        sb.append("© Gajmor Interiors - All rights reserved.<br>");
        sb.append("<a href=\"https://gajmordesign.com\">www.gajmordesign.com</a>");
        sb.append("</div>");

        sb.append("</body></html>");

        return sb.toString();
    }

    public String buildAdminQuoteEmail(String name, String email, String phone) {

        String logoUrl = "https://res.cloudinary.com/dbakcsfdz/image/upload/v1766310035/GajmorLogo_eubrc1.png";

        StringBuilder sb = new StringBuilder();

        sb.append("<!DOCTYPE html>");
        sb.append("<html><head><meta charset=\"UTF-8\">");
        sb.append("<style>");
        sb.append("body { font-family: 'Segoe UI', sans-serif; background-color:#f4f6f8; margin:0; padding:0; }");
        sb.append(".navbar { background:#1a1a2e; padding:20px; text-align:center; }");
        sb.append(".navbar img { max-height:90px; }");
        sb.append(".email-container { max-width:600px; margin:20px auto; background:#ffffff; border-radius:12px; padding:30px; box-shadow:0 4px 12px rgba(0,0,0,0.12); }");
        sb.append(".title { font-size:24px; font-weight:700; color:#1a1a2e; text-align:center; margin-bottom:25px; }");
        sb.append(".content { font-size:15px; color:#333333; line-height:1.7; }");
        sb.append(".label { font-weight:600; color:#1a1a2e; }");
        sb.append(".data-box { background:#f1f1f1; padding:14px; border-radius:8px; margin:8px 0 18px; }");
        sb.append(".footer { background:#1a1a2e; color:#ffffff; text-align:center; padding:18px; font-size:13px; border-radius:0 0 12px 12px; }");
        sb.append(".cta { display:inline-block; margin-top:15px; padding:10px 20px; background:#e1767e; color:#ffffff; border-radius:25px; text-decoration:none; font-size:14px; font-weight:600; }");
        sb.append("a { color:#f8d90f; text-decoration:none; }");
        sb.append("</style></head><body>");

        // Header
        sb.append("<div class=\"navbar\">");
        sb.append("<img src=\"").append(logoUrl).append("\" alt=\"Gajmor Logo\" />");
        sb.append("</div>");

        // Main container
        sb.append("<div class=\"email-container\">");
        sb.append("<div class=\"title\">New Enquiry Received</div>");
        sb.append("<div class=\"content\">");

        sb.append("<p>Hello Team,</p>");
        sb.append("<p>A new enquiry has been submitted via the <strong>Gajmor website</strong>. Details are below:</p>");

        sb.append("<p class=\"label\">Customer Name</p>");
        sb.append("<div class=\"data-box\">").append(name).append("</div>");

        sb.append("<p class=\"label\">Email Address</p>");
        sb.append("<div class=\"data-box\">").append(email).append("</div>");

        sb.append("<p class=\"label\">Mobile Number</p>");
        sb.append("<div class=\"data-box\">").append(phone).append("</div>");

        sb.append("<p>Please contact the customer at the earliest.</p>");

        sb.append("<a href=\"mailto:").append(email).append("\" class=\"cta\">Reply to Customer</a>");

        sb.append("</div>");
        sb.append("</div>");

        // Footer
        sb.append("<div class=\"footer\">");
        sb.append("This is an automated notification from <strong>Gajmor Interiors</strong><br>");
        sb.append("<a href=\"https://gajmordesign.com\">www.gajmordesign.com</a>");
        sb.append("</div>");

        sb.append("</body></html>");

        return sb.toString();
    }


}
