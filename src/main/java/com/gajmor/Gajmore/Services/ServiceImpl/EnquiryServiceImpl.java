package com.gajmor.Gajmore.Services.ServiceImpl;

import com.gajmor.Gajmore.Model.Enquiry;
import com.gajmor.Gajmore.Model.Feedback;
import com.gajmor.Gajmore.Repository.EnquiryRepository;
import com.gajmor.Gajmore.Services.EnquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryServiceImpl implements EnquiryService {
    @Autowired
    private EnquiryRepository enquiryRepository;

    public List<Enquiry> getAllFeedbacks() {
        return enquiryRepository.findAll();
    }

    @Override
    public void deleteById(Long id){
        enquiryRepository.deleteById(id);
    }

}
