package com.ccmt.code.driver.service;

import com.sliit.cde.driver.ProcessExitDetector;
import com.sliit.cde.driver.ProcessListener;

import java.io.IOException;

public class BootstrapService {
    private static Process process;
    private static final String ROOT_FOLDER_LOCATION = "";
    private static final String DATA_FOLDER_LOCATION =
            String.format("%s\\AppData\\Roaming\\3sinfobase\\license", System.getProperty("user.home"));

    public static void openApp() {
        try {
            process = Runtime.getRuntime().exec(
//                    ROOT_FOLDER_LOCATION +
                            "ccmt-electron.exe",
                    null);
            System.out.println(process);
            ProcessExitDetector processExitDetector = new ProcessExitDetector(process);
            processExitDetector.addProcessListener(new ProcessListener() {
                public void processFinished(Process process) {
                    System.out.println("The subprocess has finished.");
                    System.exit(0);
                }
            });
            processExitDetector.start();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void closeApp() {
        try {
            process.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void engineShutDown() {
        System.exit(0);
    }

    public static void shutDown() {
        closeApp();engineShutDown();
    }

    public static String getRootFolderLocation() {
        return ROOT_FOLDER_LOCATION;
    }

    public static String getDataFolderLocation() {
        return DATA_FOLDER_LOCATION;
    }
}
