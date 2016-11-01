package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.json.Apps;

import java.io.File;
import java.util.List;

/**
 * Created by michi on 28.08.16.
 */
public class GetApps implements Runnable {
    public void run() {
        try {
            doGetApps();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void doGetApps() throws Exception {
        ClientResponse response;
        List<Apps> appList;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Apps>> mapType = new TypeReference<List<Apps>>() {};

        response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_APP_LIST + Globals.API_OUTPUT);
        String output = response.getEntity(String.class);
        appList = objectMapper.readValue(output, mapType);
        File appSettingsFolder = new File (Globals.APP_SETTINGS_FOLDER);

        if (!appSettingsFolder.exists() && !output.isEmpty()) {
            Boolean result = appSettingsFolder.mkdir();
            if (!result) {
                throw new RuntimeException(Globals.ERROR_FOLDER);
            }
        }

        for (Apps app : appList) {
            GetAppSettings.doGetAppSettings(app);
            GetBts.doGetBusinessTransactions(app);
            GetTiers.doGetTiers(app);
            GetNodes.doGetNodes(app);
        }
    }

}
