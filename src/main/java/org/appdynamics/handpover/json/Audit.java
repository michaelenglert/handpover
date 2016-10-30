package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
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

    public String toString() {
        return  Globals.OPENING_SBRACKETS + timeStamp + Globals.SPACE + auditDateTime + Globals.SPACE + accountName + Globals.SPACE + securityProviderType + Globals.SPACE + userName + Globals.SPACE + action + Globals.SPACE + objectType + Globals.SPACE + objectName + Globals.CLOSING_SBRACKETS;
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
}
