package com.gajmor.Gajmore.Controllers;

import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PagesController {

	// user
	@GetMapping("/")
	public String homePage() {
		return "Home";
	}

	// Open The Enquiry Form
	@GetMapping("/enquiry")
	public String showEnquiryForm() {
		return "enquiryForm";
	}

	@GetMapping("/careers-page")
	public String showCareersForm() {
		return "careers";
	}

    @Value("${app.base-url}")
    private String baseUrl;
	@GetMapping("/view-pro")
	public String showViewProjects(Model model) {
        model.addAttribute("baseUrl", baseUrl);
		return "ViewProjects";
	}

	@GetMapping("/developerSection")
	public String showDeveloperSection() {
		return "developers";
	}

    @GetMapping("/aboutUs")
    public String getAboutUs(){
        return "aboutUs";
    }

    @GetMapping("/services")
    public String getServicePage(){
        return "services";
    }

    @GetMapping("/jobs")
    public String getJobPage(){
        return "job";
    }

    @GetMapping("/viewBlogs")
    public String viewBlogs(){
        return "blogs";
    }

	// admin roles
	@GetMapping("/login")
	public String showAdminLogin(HttpSession session,HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        return "index";
	}

	@GetMapping("/adminHome")
	public String showAdminHome(HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        if (session.getAttribute("adminLogin") == null){
            return "index";
        }
        return "AdminHome";
	}

	@GetMapping("/add-Projects")
	public String showAddProjectForm(HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
		if (session.getAttribute("adminLogin") == null){
            return "index";
        }
        return "addProject";
	}

	@GetMapping("/admin-view-pro")
	public String showAdminViewProjects(HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        if (session.getAttribute("adminLogin") == null){
            return "index";
        }
        return "AdminViewProject";
	}

	@GetMapping("/add-Blog")
	public String showAddBlogsForm(HttpSession  session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies
        if (session.getAttribute("adminLogin") == null){
            return "index";
        }
        return "AdminAddBlogs";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session, HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
        response.setDateHeader("Expires", 0); // Proxies


        System.out.println("Session is :: "+ session.getAttribute("adminLogin"));
        session.removeAttribute("adminLogin");
        session.invalidate();
        return "redirect:/login";
	}


}
