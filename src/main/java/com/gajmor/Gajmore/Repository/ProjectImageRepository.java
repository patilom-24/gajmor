package com.gajmor.Gajmore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gajmor.Gajmore.Model.Project;
import com.gajmor.Gajmore.Model.ProjectImage;

public interface ProjectImageRepository extends  JpaRepository<ProjectImage, Long> {
	ProjectImage findByProjectAndImagePath(Project project, String imagePath);
}
