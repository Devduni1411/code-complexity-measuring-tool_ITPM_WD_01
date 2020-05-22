package com.ccmt.code.submodule.repository;

import com.ccmt.code.submodule.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepo extends JpaRepository<Analysis, String> {
}
