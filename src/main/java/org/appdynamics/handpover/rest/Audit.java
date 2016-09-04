package org.appdynamics.handpover.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;
import org.appdynamics.handpover.config.Globals;
import org.appdynamics.handpover.export.Excel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by michi on 31.08.16.
 */
public class Audit {
    public String timeStamp;
    public String auditDateTime;
    public String accountName;
    public String securityProviderType;
    public String userName;
    public String action;
    public String objectType;
    public String objectName;

    public Audit() {

    }
    public Audit(String timeStamp, String auditDateTime, String accountName, String securityProviderType, String userName, String action, String objectType, String objectName) {
        this.timeStamp = timeStamp;
        this.auditDateTime = auditDateTime;
        this.accountName = accountName;
        this.securityProviderType = securityProviderType;
        this.userName = userName;
        this.action = action;
        this.objectType = objectType;
        this.objectName = objectName;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public String getAuditDateTime() {
        return auditDateTime;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getSecurityProviderType() {
        return securityProviderType;
    }

    public String getUserName() {
        return userName;
    }

    public String getAction() {
        return action;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getObjectName() {
        return objectName;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + timeStamp + Globals.SPACE + auditDateTime + Globals.SPACE + accountName + Globals.SPACE + securityProviderType + Globals.SPACE + userName + Globals.SPACE + action + Globals.SPACE + objectType + Globals.SPACE + objectName + Globals.CLOSING_SBRACKETS;
    }

    public static void doGetAudit() throws Exception{
        DateFormat dateFormat = new SimpleDateFormat(Globals.AUDIT_TIMESTAMP);
        Calendar cal = GregorianCalendar.getInstance();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Audit>> mapType = new TypeReference<List<Audit>>() {};
        Date start;
        Date end;
        List<Audit> jsonToList;
        Date current = new Date();
        cal.add(Calendar.DAY_OF_YEAR, Globals.API_AUDIT_DAYS);
        start = cal.getTime();
        Excel excel = new Excel();
        excel.openFile();
        int rowIndex = 1;
        while (start.before(current)) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            end = cal.getTime();
            ClientResponse response;
            response = Base.getClientResponse(Globals.URL + Globals.CONTROLLER_ROOT + Globals.API_AUDIT + Globals.API_AUDIT_START + dateFormat.format(start) + Globals.AUDIT_TIMESTAMP_END + Globals.API_AUDIT_END + dateFormat.format(end) + Globals.AUDIT_TIMESTAMP_END);
            String output = response.getEntity(String.class);

            if (!output.equals("[]")) {
                jsonToList = objectMapper.readValue(output, mapType);
                System.out.println(jsonToList);
                Iterator<Audit> iterator = jsonToList.iterator();
                while(iterator.hasNext()){
                    Audit audit = iterator.next();
                    excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 0, audit.getAuditDateTime());
                    excel.writeToFile(Globals.EXCEL_CONTROLLER_AUDIT, rowIndex, 1, audit.getAccountName());
                    rowIndex++;
                }
            }

            cal.add(Calendar.DAY_OF_YEAR, 1);
            start = cal.getTime();
        }
        excel.closeFile();
    }
}
