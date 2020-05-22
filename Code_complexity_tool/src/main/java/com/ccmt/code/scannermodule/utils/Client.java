package com.ccmt.code.scannermodule.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ccmt.code.coremodule.model.Project;
import com.ccmt.code.coremodule.model.ProjectFile;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.IOException;

public class Client {

    private static final Logger LOGGER = Logger.getLogger(Client.class);
    public static final String FAILED_TO_SUBMIT_DATA_TO_THE_SERVER = "Failed to submit data to the server ";
    private static String accUrl = PropertyReader.getInstance().getProperty("accUrl");
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();

    private Client() {
    }

    public static void sendAnalysisData(Project project) {
        try {

            System.out.println("\n\n\n\n ID TEST \n\n\n\n");
            System.out.println(project.getFiles().get(0).getId());
            ProjectFile pf = new ProjectFile();

            LOGGER.debug("Submitting analysis data to the server...");

            HttpPost request = new HttpPost(accUrl);
            request.addHeader("content-type", "application/json");

            String json = gson.toJson(project); // convert
            LOGGER.info(json);

            StringEntity params = new StringEntity(json);
            request.setEntity(params);

            if (project.getFiles().size() > 0) {
                HttpResponse response = httpClient.execute(request);

                if (response.getStatusLine().getStatusCode() == 200) {
                    LOGGER.info("Analysis data submitted to the server " + response.getStatusLine());
                } else {
                    LOGGER.error(FAILED_TO_SUBMIT_DATA_TO_THE_SERVER + response.getStatusLine());
                }
            } else {
                LOGGER.error("Nothing to Submit");
            }

        } catch (IOException e) {
            LOGGER.error(FAILED_TO_SUBMIT_DATA_TO_THE_SERVER);
        }
    }
}
