package com.gajmor.Gajmore.Controllers;

import com.gajmor.Gajmore.Model.Enquiry;
import com.gajmor.Gajmore.Services.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enquiry")
@CrossOrigin("*")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @GetMapping("/all")
    public List<Enquiry> getAllEnquiries() {
        return enquiryService.getAllFeedbacks();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEnquiry(@PathVariable Long id){
        // you can add existsById check if you want
        enquiryService.deleteById(id);
    }
}
