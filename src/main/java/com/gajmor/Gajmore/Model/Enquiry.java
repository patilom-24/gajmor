package com.gajmor.Gajmore.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "enquiry") // ensure this matches your DB table
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  // match with DB column
    private long id;

    @Column(name = "customer_name", nullable = false)
    private String name;

    @Column(name = "customer_email", nullable = false)
    private String email;

    @Column(name = "customer_mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "enquiry_for", nullable = false)
    private String enquiryFor;

    @Column(name = "date_of_enquiry", nullable = false)
    private LocalDateTime enquiryDate;

    @PrePersist
    public void setEnquiryDate() {
        this.enquiryDate = LocalDateTime.now();
    }


}
