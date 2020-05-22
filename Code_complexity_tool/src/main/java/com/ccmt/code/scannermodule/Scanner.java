package com.ccmt.code.scannermodule;

import com.ccmt.code.scannermodule.acc.FileHandler;
import com.ccmt.code.coremodule.model.Project;
import org.apache.log4j.Logger;

public class Scanner {
    private static final Logger LOGGER = Logger.getLogger(Scanner.class);

    static void execute(Project project) {

        LOGGER.info("Analyzing project " + project.getProjectKey() + "...");
        LOGGER.info("Found source path " + project.getSourcePath());

        new FileHandler().readFiles(project);

    }

    /**
     *
     * @param projectKey
     * @param sourcePath
     */
    public static void startScanJob(String projectKey, String sourcePath) {

        org.apache.log4j.PropertyConfigurator.configure("config/log4j.properties");
        LOGGER.debug("ACCScanner start analyzing...");

        Project project = new Project();
        project.setProjectKey(projectKey);
        project.setSourcePath(sourcePath);

        execute(project);

    }
}
