package com.gajmor.Gajmore.Services;

import com.gajmor.Gajmore.Model.Enquiry;

import java.util.List;

public interface EnquiryService {
    List<Enquiry> getAllFeedbacks();

    void deleteById(Long id);
}
