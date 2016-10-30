package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.json.Settings;

import java.util.List;

/**
 * Created by michi on 31.08.16.
 */
@SuppressWarnings("WeakerAccess")
public class GetSettings {

    public static void doGetControllerSettings() throws Exception{
        ClientResponse response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_CONTROLLER_CONFIG + Globals.API_OUTPUT);
        TypeReference<List<Settings>> mapType = new TypeReference<List<Settings>>() {};
        ObjectMapper objectMapper = new ObjectMapper();
        List<Settings> settingsList;

        String output = response.getEntity(String.class);
        settingsList = objectMapper.readValue(output, mapType);
        GetSettings.doWriteControllerSettings(settingsList);
    }

    public static void doWriteControllerSettings(List<Settings> settingsList) throws Exception {
        Excel excel = new Excel();
        excel.openFile();
        int rowIndex = 1;

        for (Settings settings : settingsList)
        {
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 0, settings.getName());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 1, settings.getDescription());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 2, settings.getScope());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 3, settings.getUpdateable());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 4, settings.getValue());
            rowIndex++;
        }
        excel.closeFile();
    }
}
