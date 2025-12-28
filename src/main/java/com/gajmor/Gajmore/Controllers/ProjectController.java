package com.gajmor.Gajmore.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gajmor.Gajmore.Model.Project;
import com.gajmor.Gajmore.Services.ServiceImpl.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping("/list")
	@ResponseBody
	public List<Project> allProjects() {
		return projectService.getAllProjects();
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> saveProject(@RequestParam(value = "id", required = false) Long id,
			@RequestParam("name") String name, @RequestParam("projectType") String projectType,
			@RequestParam("location") String location, @RequestParam(value = "area", required = false) Double area,
			@RequestParam("description") String description,
			@RequestParam(value = "images", required = false) MultipartFile[] files) {
		try {
			if (id == null && (files == null || files.length == 0 || files[0].isEmpty())) {
				return ResponseEntity.badRequest().body("Please upload at least one image for a new project.");
			}

			if (files != null && files.length > 25) {
				return ResponseEntity.badRequest().body("⚠️ Maximum 25 images allowed per request.");
			}

			Project project = projectService.saveProject(id, name, projectType, location, area, description, files);

			String message = (id == null)
					? "✅ Project added successfully with " + (files != null ? files.length : 0) + " images!"
					: "✅ Project updated successfully with " + (files != null ? files.length : 0) + " new images!";

			return ResponseEntity.ok().body(message);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("❌ Failed to save project: " + e.getMessage());
		}
	}

	// ********* UPDATED UPDATE METHOD *********
	@PutMapping("/update/{id}")
	public ResponseEntity<Project> updateProject(@PathVariable Long id, @RequestParam("projectName") String projectName,
			@RequestParam("projectDescription") String projectDescription,
			@RequestParam("projectType") String projectType,
			@RequestParam(value = "images", required = false) List<MultipartFile> images,
			@RequestParam(value = "deletedImages", required = false) List<String> deletedImages) {

		try {
			Project updatedProject = projectService.updateProject(id, projectName, projectDescription, projectType,
					images, deletedImages);
			return ResponseEntity.ok(updatedProject);
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	// *****************************************

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
		boolean deleted = projectService.deleteProject(id);
		if (deleted) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	// ************** FIXED DELETE IMAGE API **************
	@DeleteMapping("/{id}/deleteImage")
	public ResponseEntity<Void> deleteImage(@PathVariable Long id, @RequestParam String path) {
		projectService.deleteImage(id, path);
		return ResponseEntity.ok().build();
	}
	// ****************************************************

}
