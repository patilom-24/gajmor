package com.gajmor.Gajmore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gajmor.Gajmore.Model.Project;

public interface ProjectRepository extends JpaRepository<Project,Long> {

}
