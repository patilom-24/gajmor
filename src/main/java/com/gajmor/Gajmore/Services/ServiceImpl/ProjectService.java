package com.gajmor.Gajmore.Services.ServiceImpl;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gajmor.Gajmore.Model.Project;
import com.gajmor.Gajmore.Model.ProjectImage;
import com.gajmor.Gajmore.Repository.ProjectImageRepository;
import com.gajmor.Gajmore.Repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private ProjectImageRepository imageRepository;

	@Value("${project.upload.dir}")
	private String uploadDir;

	public ProjectService(ProjectRepository projectRepo) {
		this.projectRepo = projectRepo;
	}

	public Project saveProject(Long id, String name, String projectType, String location, Double area,
			String description, MultipartFile[] files) throws IOException {

		Project project;

		if (id != null) {
			project = projectRepo.findById(id).orElse(new Project());
		} else {
			project = new Project();
		}

		project.setName(name);
		project.setProjectType(projectType);
		project.setLocation(location);
		project.setArea(area);
		project.setDescription(description);

		if (files != null && files.length > 0) {
			File uploadFolder = new File(uploadDir);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			if (project.getImages() == null) {
				project.setImages(new java.util.ArrayList<>());
			}

			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					String fileName = System.currentTimeMillis() + "_"
							+ file.getOriginalFilename().replaceAll("\\s+", "_");

					File dest = new File(uploadFolder, fileName);
					file.transferTo(dest);

					ProjectImage img = new ProjectImage();
					img.setImagePath("/" + fileName);
					img.setProject(project);

					project.getImages().add(img);
				}
			}
		}

		return projectRepo.save(project);
	}

	// ******************* UPDATED UPDATE METHOD WITH DELETED IMAGES
	// *******************
	public Project updateProject(Long id, String projectName, String projectDescription, String projectType,
			List<MultipartFile> images, List<String> deletedImages) throws IOException {

		Project project = projectRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Project not found with id " + id));

		project.setName(projectName);
		project.setDescription(projectDescription);
		project.setProjectType(projectType);

		// Handle deleted images
		if (deletedImages != null && !deletedImages.isEmpty()) {

			Iterator<ProjectImage> iterator = project.getImages().iterator();

			while (iterator.hasNext()) {
				ProjectImage img = iterator.next();

				if (deletedImages.contains(img.getImagePath())) {
					File file = new File(uploadDir, img.getImagePath().replaceFirst("^/+", ""));
					if (file.exists()) {
						file.delete();
					}
					imageRepository.delete(img);
					iterator.remove();
				}
			}
		}

		// Handle new images
		if (images != null && !images.isEmpty()) {
			File uploadFolder = new File(uploadDir);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}

			if (project.getImages() == null) {
				project.setImages(new java.util.ArrayList<>());
			}

			for (MultipartFile file : images) {
				if (!file.isEmpty()) {
					String fileName = System.currentTimeMillis() + "_"
							+ file.getOriginalFilename().replaceAll("\\s+", "_");

					File dest = new File(uploadFolder, fileName);
					file.transferTo(dest);

					ProjectImage img = new ProjectImage();
					img.setImagePath("/" + fileName);
					img.setProject(project);

					project.getImages().add(img);
				}
			}
		}

		return projectRepo.save(project);
	}
	// *********************************************************************************

	public List<Project> getAllProjects() {
		return projectRepo.findAll();
	}

	public boolean deleteProject(Long id) {
		Optional<Project> projectOpt = projectRepo.findById(id);

		if (projectOpt.isPresent()) {
			Project project = projectOpt.get();

			for (ProjectImage img : project.getImages()) {
				File file = new File(uploadDir, img.getImagePath().replaceFirst("^/+", ""));
				if (file.exists()) {
					file.delete();
				}
				imageRepository.delete(img);
			}

			projectRepo.delete(project);
			return true;
		}
		return false;
	}

	@Transactional
	public void deleteImage(Long projectId, String path) {
		Project project = projectRepo.findById(projectId).orElseThrow();

		ProjectImage image = project.getImages().stream().filter(img -> img.getImagePath().equals(path)).findFirst()
				.orElseThrow();

		project.getImages().remove(image);
		imageRepository.delete(image);

		File file = new File(uploadDir, path.replaceFirst("^/+", ""));
		if (file.exists()) {
			file.delete();
		}
	}
}
