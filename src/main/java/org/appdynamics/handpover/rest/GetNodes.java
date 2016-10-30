package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.json.Apps;
import org.appdynamics.handpover.json.Nodes;

import java.util.List;

/**
 * Created by michi on 19.09.16.
 */
@SuppressWarnings("WeakerAccess")
public class GetNodes {
    public static void doGetNodes (Apps app) throws Exception {
        ClientResponse response;
        List<Nodes> nodeList;
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Nodes>> mapType = new TypeReference<List<Nodes>>() {};

        response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_APP_LIST + Globals.ROOT + app.getId() + Globals.API_NODE_LIST + Globals.API_OUTPUT);
        String output = response.getEntity(String.class);

        if (!output.equals("[]")){
            nodeList = objectMapper.readValue(output, mapType);
            doWriteNodes(app, nodeList);
        }

    }
    private static void doWriteNodes (Apps app, List<Nodes> nodeList) throws Exception{
        Excel excel = new Excel();

        excel.openFile();

        int rowIndex = 0;

        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 0, Globals.ID + Globals.COLON + Globals.SPACE + app.getId());
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 1, Globals.NAME + Globals.COLON + Globals.SPACE + app.getName());
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 2, app.getDescription());

        rowIndex++;
        rowIndex++;

        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 0, "Name");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 1, "Id");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 2, "AgentType");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 3, "AppAgentPresent");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 4, "AppAgentVersion");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 5, "TierName");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 6, "TierId");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 7, "Type");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 8, "MachineAgentPresent");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 9, "MachineAgentVersion");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 10, "MachineId");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 11, "MachineName");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 12, "MachineOSType");
        excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 13, "NodeUniqueLocalId");

        rowIndex++;

        for (Nodes node : nodeList) {
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 0, node.getName());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 1, node.getId());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 2, node.getAgentType());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 3, node.getAppAgentPresent());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 4, node.getAppAgentVersion());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 5, node.getTierName());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 6, node.getTierId());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 7, node.getType());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 8, node.getMachineAgentPresent());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 9, node.getMachineAgentVersion());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 10, node.getMachineId());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 11, node.getMachineName());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 12, node.getMachineOSType());
            excel.writeToFile(app.getId() + Globals.EXCEL_NODES, rowIndex, 13, node.getNodeUniqueLocalId());

            rowIndex++;
        }
        excel.closeFile();
    }
}
