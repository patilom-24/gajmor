package com.gajmor.Gajmore.Controllers;

import com.gajmor.Gajmore.Model.GoogleReview;
import com.gajmor.Gajmore.Repository.GoogleReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public class GoogleReviewController {
    @Autowired
    private GoogleReviewRepository repo;

    @PostMapping
    public void save(@RequestBody List<Map<String, Object>> reviews) {

        for (Map<String, Object> r : reviews) {
            GoogleReview gr = new GoogleReview();
            gr.setAuthorName((String) r.get("author_name"));
            gr.setRating((int) r.get("rating"));
            gr.setText((String) r.get("text"));
            gr.setProfilePhotoUrl((String) r.get("profile_photo_url"));

            repo.save(gr);
        }
    }

    @GetMapping
    public List<GoogleReview> getAll() {
        return repo.findAll();
    }
}
