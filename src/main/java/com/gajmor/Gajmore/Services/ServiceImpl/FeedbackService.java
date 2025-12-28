package com.gajmor.Gajmore.Services.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gajmor.Gajmore.Model.Feedback;
import com.gajmor.Gajmore.Repository.FeedbackRepository;
@Service
public class FeedbackService{

    @Autowired
    private FeedbackRepository repository;

    public Feedback saveFeedback(Feedback feedback) {
        return repository.save(feedback);
    }
}
