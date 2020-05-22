/**
 *
 */
package com.ccmt.code.submodule.service;

import com.ccmt.code.submodule.model.Analysis;
import com.ccmt.code.submodule.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
	public Project save(Project project);

	public Optional<Project> getByKey(String projectKey);

	public List<Project> getAll();

	public List<Analysis> getHistoryByKey(String key);
}
