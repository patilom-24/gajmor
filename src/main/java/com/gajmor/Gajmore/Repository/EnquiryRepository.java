package com.gajmor.Gajmore.Repository;

import com.gajmor.Gajmore.Model.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnquiryRepository extends JpaRepository<Enquiry,Long> {

    List<Enquiry> findAll();
}
