package com.gajmor.Gajmore.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AdminLoginController {

    @PostMapping("/loginAdmin")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        if ("admin".equals(username) && "admin123".equals(password)) {

            session.setAttribute("adminLogin", "loginSuccess");

            // ✅ success message for next request only
            redirectAttributes.addFlashAttribute("success", "Welcome Gajanan Morye!");

            return "redirect:/adminHome";

        } else {
            // ✅ error message for next request only
            redirectAttributes.addFlashAttribute("error", "Invalid Credentials");

            return "redirect:/login";
        }
    }

}
