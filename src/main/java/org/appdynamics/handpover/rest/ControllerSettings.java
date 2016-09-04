package org.appdynamics.handpover.rest;

/**
 * Created by michi on 29.08.16.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;

import java.util.Iterator;
import java.util.List;

public class ControllerSettings {
    public String description;
    public String name;
    public String scope;
    public String updateable;
    public String value;

    public ControllerSettings() {

    }
    public ControllerSettings(String description, String name, String scope, String updateable, String value) {
        this.description = description;
        this.name = name;
        this.scope = scope;
        this.updateable = updateable;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getScope() {
        return scope;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdateable() {
        return updateable;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + description + Globals.SPACE + name + Globals.SPACE + scope + Globals.SPACE + updateable + Globals.SPACE + value + Globals.CLOSING_SBRACKETS;
    }

    public static void doGetControllerSettings() throws Exception{
        ClientResponse response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_CONTROLLER_CONFIG + Globals.API_OUTPUT);
        assert response != null;
        String output = response.getEntity(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ControllerSettings>> mapType = new TypeReference<List<ControllerSettings>>() {};
        List<ControllerSettings> jsonToList = null;
        jsonToList = objectMapper.readValue(output, mapType);
        ControllerSettings.doWriteControllerSettings(jsonToList);
    }

    public static void doWriteControllerSettings(List<ControllerSettings> controllerList) throws Exception {
        Iterator<ControllerSettings> iterator = controllerList.iterator();
        Excel excel = new Excel();
        excel.openFile();
        int rowIndex = 1;
        while(iterator.hasNext()){
            ControllerSettings controller = iterator.next();
            excel.writeToFile(Globals.EXCEL_CONTROLLER_SETTINGS, rowIndex, 5, controller.getValue());
            rowIndex++;
        }
        excel.closeFile();
    }
}
