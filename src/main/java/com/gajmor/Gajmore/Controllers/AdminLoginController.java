package com.gajmor.Gajmore.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminLoginController {

    @PostMapping("/loginAdmin")
    public String adminLogin(@RequestParam String username,
                             @RequestParam String password,
                             Model model,
                             HttpSession session){

        if (username.equals("admin") && password.equals("admin123")){
            session.setAttribute("adminLogin","loginSuccess");
            System.out.println("Session activated :: "+session.getAttribute("adminLogin"));
            model.addAttribute("success", "Welcome Admin!");

            return "AdminHome";
        }
        else {
            model.addAttribute("error","Invalidate Credentials");
            return "index";
        }

    }
}
