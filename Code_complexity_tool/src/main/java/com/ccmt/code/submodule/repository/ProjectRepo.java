package com.ccmt.code.submodule.repository;

import com.ccmt.code.submodule.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
    @Query(value = "SELECT p FROM Project p WHERE p.projectKey = :projectKey")
    Optional<Project> findByKey(@Param("projectKey") String projectKey);
}
