package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.json.Apps;
import org.appdynamics.handpover.json.BusinessTransactions;

import java.util.List;

/**
 * Created by michi on 19.09.16.
 */
@SuppressWarnings("WeakerAccess")
public class GetBts {
    public static void doGetBusinessTransactions (Apps app) throws Exception {
        ClientResponse response;
        List<BusinessTransactions> btList;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<BusinessTransactions>> mapType = new TypeReference<List<BusinessTransactions>>() {};

        response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_APP_LIST + Globals.ROOT + app.getId() + Globals.API_BT_LIST + Globals.API_OUTPUT);
        String output = response.getEntity(String.class);

        if (!output.equals("[]")){
            btList = objectMapper.readValue(output, mapType);
            doWriteBusinessTransactions(app, btList);
        }

    }
    private static void doWriteBusinessTransactions (Apps app, List<BusinessTransactions> btList) throws Exception {
        Excel excel = new Excel();
        excel.openFile();

        int rowIndex = 0;

        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 0, Globals.ID + Globals.COLON + Globals.SPACE + app.getId());
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 1, Globals.NAME + Globals.COLON + Globals.SPACE + app.getName());
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 2, app.getDescription());

        rowIndex++;
        rowIndex++;

        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 0, "Name");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 1, "InternalName");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 2, "Id");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 3, "Background");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 4, "EntryPointType");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 5, "TierName");
        excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 6, "TierId");

        rowIndex++;

        for (BusinessTransactions bt : btList) {
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 0, bt.getName());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 1, bt.getInternalName());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 2, bt.getId());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 3, bt.getBackground());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 4, bt.getEntryPointType());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 5, bt.getTierName());
            excel.writeToFile(app.getId() + Globals.EXCEL_BTS, rowIndex, 6, bt.getTierId());

            rowIndex++;
        }
        excel.closeFile();
    }
}
