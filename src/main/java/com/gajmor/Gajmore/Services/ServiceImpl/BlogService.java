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

 
    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }
}
