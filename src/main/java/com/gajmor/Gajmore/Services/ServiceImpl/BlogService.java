package com.gajmor.Gajmore.Services.ServiceImpl;

import com.gajmor.Gajmore.Model.*;
import com.gajmor.Gajmore.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BlogService {

	@Autowired
    private BlogRepository blogRepository;

    @Value("${project.upload.dir}")
    private String uploadDir;

    public Blog addBlog(String title, String author, String content, MultipartFile imageFile) throws IOException {
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setAuthor(author);
        blog.setContent(content);

        // Save image if exists
        if (imageFile != null && !imageFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            Files.write(path, imageFile.getBytes());
            blog.setImagePath("/" + fileName);
        }

        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, String title, String author, String content, MultipartFile imageFile)
            throws IOException {

        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        blog.setTitle(title);
        blog.setAuthor(author);
        blog.setContent(content);

        // ✅ FIX: use SAME uploadDir as addBlog
        if (imageFile != null && !imageFile.isEmpty()) {

            // 1️⃣ Delete old image (from correct folder)
            if (blog.getImagePath() != null && !blog.getImagePath().isEmpty()) {
                Path oldImagePath = Paths.get(uploadDir, blog.getImagePath());
                Files.deleteIfExists(oldImagePath);
            }

            // 2️⃣ Save new image
            String newFileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
            Path newImagePath = Paths.get(uploadDir, newFileName);

            File dir = new File(uploadDir);
            if (!dir.exists()) dir.mkdirs();

            Files.write(newImagePath, imageFile.getBytes());

            // 3️⃣ Update DB with new filename
            blog.setImagePath(newFileName);
        }

        return blogRepository.save(blog);
    }


    public void deleteBlogById(Long id) {
        blogRepository.deleteById(id);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
