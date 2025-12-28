package com.gajmor.Gajmore.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gajmor.Gajmore.Model.Blog;
import com.gajmor.Gajmore.Services.ServiceImpl.BlogService;

import java.io.IOException;

@RestController
@RequestMapping("/blogs")
@CrossOrigin("*")
public class BlogController {

    @Autowired
	private BlogService blogService;

    @PostMapping("/add")
    public ResponseEntity<?> addBlog(
            @RequestParam("title") String title,
            @RequestParam("author") String author,
            @RequestParam("content") String content,
            @RequestParam(value = "image", required = false) MultipartFile imageFile
    ) {
        try {
            Blog saved = blogService.addBlog(title, author, content, imageFile);
            return ResponseEntity.ok(saved);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error saving blog: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long id) {
        try {
            blogService.deleteBlogById(id);
            return ResponseEntity.ok("Blog deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting blog: " + e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<?> getAllBlogs() {
        return ResponseEntity.ok(blogService.getAllBlogs());
    }
}
