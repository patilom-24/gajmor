package com.gajmor.Gajmore.Controllers;
import com.gajmor.Gajmore.Services.ServiceImpl.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.gajmor.Gajmore.Model.Feedback;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService service;

    @PostMapping("/add")
    public ResponseEntity<?> addFeedback(@RequestBody Feedback feedback) {
        return ResponseEntity.ok(service.saveFeedback(feedback));
    }

    @GetMapping("/feedbacks")
    public List<Feedback> getFeedbacks() {
        return  service.getAllFeedbacks();
    }
}
