package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.json.Apps;
import org.appdynamics.handpover.json.Tiers;

import java.util.List;

/**
 * Created by michi on 19.09.16.
 */
public class GetTiers {
    public static void doGetTiers (Apps app) throws Exception {
        ClientResponse response;
        List<Tiers> tierList;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Tiers>> mapType = new TypeReference<List<Tiers>>() {};

        response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_APP_LIST + Globals.ROOT + app.getId() + Globals.API_TIER_LIST + Globals.API_OUTPUT);
        String output = response.getEntity(String.class);

        if (!output.equals("[]")){
            tierList = objectMapper.readValue(output, mapType);
            doWriteTiers(app, tierList);
        }

    }
    private static void doWriteTiers (Apps app, List<Tiers> tierList) throws Exception {
        Excel excel = new Excel();
        excel.openFile();

        int rowIndex = 0;

        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 0, Globals.ID + Globals.COLON + Globals.SPACE + app.getId());
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 1, Globals.NAME + Globals.COLON + Globals.SPACE + app.getName());
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 2, app.getDescription());

        rowIndex++;
        rowIndex++;

        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 0, "Name");
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 1, "Id");
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 2, "Description");
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 3, "Type");
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 4, "NumberOfNodes");
        excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 5, "AgentType");

        rowIndex++;

        for (Tiers tier : tierList) {
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 0, tier.getName());
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 1, tier.getId());
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 2, tier.getDescription());
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 3, tier.getType());
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 4, tier.getNumberOfNodes());
            excel.writeToFile(app.getId() + Globals.EXCEL_TIERS, rowIndex, 5, tier.getAgentType());
            rowIndex++;
        }
        excel.closeFile();
    }
}
