package com.gajmor.Gajmore.Controllers;

import jakarta.servlet.http.HttpSession;
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

	@GetMapping("/view-pro")
	public String showViewProjects() {
		return "ViewProjects";
	}

	@GetMapping("/devloperSection")
	public String showDeveloperSection() {
		return "developers";
	}

	// admin
	@GetMapping("/login")
	public String showAdminLogin() {
		return "index";
	}

	@GetMapping("/adminHome")
	public String showAdminHome() {
		return "AdminHome";
	}

	@GetMapping("/add-Projects")
	public String showAddProjectForm() {
		return "addProject";
	}

	@GetMapping("/admin-view-pro")
	public String showAdminViewProjects() {
		return "AdminViewProject";
	}

	@GetMapping("/add-Blog")
	public String showAddBlogsForm() {
		return "AdminAddBlogs";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
        session.removeAttribute("isAdminLoggedIn");
        return "index";
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
}
