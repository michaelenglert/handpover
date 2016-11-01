package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.json.Audit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by michi on 31.08.16.
 */
@SuppressWarnings("WeakerAccess")
public class GetAudit implements Runnable{
    @Override
    public void run() {
        try {
            doGetAudit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void doGetAudit() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat(Globals.AUDIT_TIMESTAMP);
        Calendar cal = GregorianCalendar.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Audit>> mapType = new TypeReference<List<Audit>>() {};
        List<Audit> auditList;
        Date current = new Date();
        Date start;
        Date end;

        cal.add(Calendar.DAY_OF_YEAR, Globals.API_AUDIT_DAYS);
        start = cal.getTime();

        while (start.before(current)) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            end = cal.getTime();
            ClientResponse response;
            response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_AUDIT + Globals.API_AUDIT_START + dateFormat.format(start) + Globals.AUDIT_TIMESTAMP_END + Globals.API_AUDIT_END + dateFormat.format(end) + Globals.AUDIT_TIMESTAMP_END);
            String output = response.getEntity(String.class);

            if (!output.equals("[]")) {
                auditList = objectMapper.readValue(output, mapType);
                GetAudit.doWriteAudit(auditList);
            }

            cal.add(Calendar.DAY_OF_YEAR, 1);
            start = cal.getTime();
        }
        Globals.PROGRESS = Globals.PROGRESS + 10;
    }

    public static void doWriteAudit(List<Audit> auditList) throws Exception {
        Excel excel = new Excel();
        excel.createFile(Globals.AUDIT_FILE);
        excel.openFile(Globals.AUDIT_FILE);

        int rowIndex = 1;

        for (Audit audit : auditList) {
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 0, audit.getAuditDateTime());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 1, audit.getAccountName());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 2, audit.getSecurityProviderType());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 3, audit.getUserName());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 4, audit.getAction());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 5, audit.getObjectType());
            excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 6, audit.getObjectName());
            rowIndex++;
        }
        excel.closeFile(Globals.AUDIT_FILE);
    }
}
