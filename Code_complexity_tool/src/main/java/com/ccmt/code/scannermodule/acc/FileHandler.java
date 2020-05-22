package com.ccmt.code.scannermodule.acc;

import com.ccmt.code.coremodule.model.CouplingComplexity;
import com.ccmt.code.scannermodule.acc.enums.Language;
import com.ccmt.code.scannermodule.acc.enums.Method;
import com.ccmt.code.scannermodule.acc.service.CouplingPatternService;
import com.ccmt.code.scannermodule.utils.MethodAndVariableFinder;
import com.ccmt.code.scannermodule.utils.RecursiveMethodLineNumberFinder;
import com.ccmt.code.coremodule.model.Line;
import com.ccmt.code.coremodule.model.Project;
import com.ccmt.code.coremodule.model.ProjectFile;
import com.ccmt.code.scannermodule.model.Stack;
import com.ccmt.code.scannermodule.utils.Client;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FileHandler {

    private static final Logger LOGGER = Logger.getLogger(FileHandler.class);

    public static Stack stack;
    private Project project;
    private String projectRoot = "";
    private List<File> fileList = new ArrayList<>();
    private List<ProjectFile> projectFiles = new ArrayList<>();

    public void readFiles(Project p) {
        this.project = p;
        this.projectRoot = project.getSourcePath();

        getFiles(projectRoot);
        calculateComplexity();
        this.project.setFiles(projectFiles);

        //calculate project cp
        int projectCp = 0;
        for (ProjectFile pf : projectFiles) {
            projectCp += pf.getCp();
        }
        project.setCp(projectCp);

        Client.sendAnalysisData(project);

    }

    public void getFiles(String projectPath) {
        File dir = new File(projectPath);
        File[] directoryListing = dir.listFiles();
        if (Objects.nonNull(directoryListing)) {
            for (File file : directoryListing) {
                if (file.isDirectory()) {
                    getFiles(file.getPath());
                }
                if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("java")) {
                    fileList.add(file);
                    project.setLanguage("Java");
                } else if (FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("cpp")) {
                    fileList.add(file);
                    project.setLanguage("C++");
                }
            }
        }
    }

    private void calculateComplexity() {
        LOGGER.info("Found " + fileList.size() + " Files in source path");
        fileList.forEach(file -> {
            ProjectFile projectFile = new ProjectFile();
            stack = new Stack();

            try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {

                LOGGER.debug("Analyzing file " + file.getCanonicalPath().replace(projectRoot, ""));
                projectFile.setRelativePath(file.getCanonicalPath().replace(projectRoot, ""));
                List<Line> lines = new ArrayList<>();
                boolean singleLineCommented;
                boolean multiLineCommented = false;

                // helper for Cs calculation
                List<String> methodsAndVariables = MethodAndVariableFinder.getMethodAndVariables(file);
                HashMap<Integer, Integer> recursiveLineNumbers = RecursiveMethodLineNumberFinder.getRecursiveMethodLineNumbers(file);

                Language projectLanguage = Language.JAVA;
                if (project.getLanguage().equals("Java")) {
                    projectLanguage = Language.JAVA;
                } else {
                    projectLanguage = Language.CPP;
                }
                try {
                    CouplingPatternService couplingPatternService = new CouplingPatternService();
                    CouplingComplexity couplingComplexity = couplingPatternService.getCouplingComplexity(file);
                    List<Method> methodsList = couplingPatternService.getMethodsList(file);
                    projectFile.setCouplingComplexity(couplingComplexity);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                MethodComplexityAnalyzer methodComplexityAnalyzer = new MethodComplexityAnalyzer(projectLanguage);
                VariableComplexityAnalyzer variableComplexityAnalyzer = new VariableComplexityAnalyzer(projectLanguage);
                SizeComplexityAnalyzer sizeComplexityAnalyzer = new SizeComplexityAnalyzer(projectLanguage);
                ControlStructureComplexityAnalyzer controlStructureComplexityAnalyzer =
                        new ControlStructureComplexityAnalyzer();
                InheritanceComplexityAnalyzer inheritanceComplexityAnalyzer = new InheritanceComplexityAnalyzer();
                CouplingComplexityAnalyzer couplingComplexityAnalyzer = new CouplingComplexityAnalyzer();

                for (String line; (line = lnr.readLine()) != null; ) {
                    Line lineObj = new Line();
                    lineObj.setLineNo(lnr.getLineNumber());
                    lineObj.setData(line);

                    // ignore comment lines
                    if (line.trim().startsWith("//") || line.trim().startsWith("import") ||
                            line.trim().startsWith("include")) {
                        singleLineCommented = true;
                    } else {
                        singleLineCommented = false;
                    }
                    if (line.trim().startsWith("/*")) {
                        multiLineCommented = true;
                    }
                    if (line.trim().startsWith("*/")) {
                        line = line.replaceFirst("\\*/", "");
                        multiLineCommented = false;
                    }
                    if (line.contains("//")) {
                        line = line.replace(line.substring(line.indexOf("//")), "");
                    }

                    //calculate complexity if line is not commented
                    if (!singleLineCommented && !multiLineCommented) {
                        methodComplexityAnalyzer.analyze(lineObj, line);
                        variableComplexityAnalyzer.analyze(lineObj, line);
                        sizeComplexityAnalyzer.analyze(lineObj, line);
                        controlStructureComplexityAnalyzer.analyze(lineObj, line);
                        inheritanceComplexityAnalyzer.analyze(lineObj, line, projectLanguage);
//                        couplingComplexityAnalyzer.analyze(lineObj, line, methodsList);
                        Cs.calcCs(lineObj, line, methodsAndVariables);
                        Ci.calcCi(lineObj, line, project.getLanguage());
                        Ctc.calcCtc(lineObj, line);
                        Cnc.calcCnc(lineObj, line);
                        Cr.calcCr(lineObj, recursiveLineNumbers);
                    }

                    if (line.trim().endsWith("*/")) {
                        multiLineCommented = false;
                    }

                    lines.add(lineObj);
                }

                int fileCp = 0;
                for (Line line : lines) {
                    if (line.getCr() != 0) {
                        fileCp += line.getCr();
                    } else {
                        fileCp += line.getCps();
                    }
                }

                projectFile.setCp(fileCp);
                projectFile.setLinesData(lines);

                projectFiles.add(projectFile);
                RecursiveMethodLineNumberFinder.resetData();
                Ci.resetCi(); //reset ci value after file ends
                Ctc.setSwitchCtc(); //add switch ctc value

            } catch (IOException e) {
                LOGGER.error("Error reading file", e);
            }
        });
    }
}
