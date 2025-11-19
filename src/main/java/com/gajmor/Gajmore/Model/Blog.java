package com.gajmor.Gajmore.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "blogs")
@Data
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @Column(length = 10000) // large content
    private String content;

    private String imagePath; // store image path


}
