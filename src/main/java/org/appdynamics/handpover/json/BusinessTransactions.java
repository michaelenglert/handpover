package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 18.09.16.
 */
public class BusinessTransactions {
    public String id;
    public String name;
    public String entryPointType;
    public String internalName;
    public String tierId;
    public String tierName;
    public String background;

    public BusinessTransactions(){}

    public BusinessTransactions (String id, String name, String entryPointType, String internalName, String tierId, String tierName, String background){
        this.id = id;
        this.name = name;
        this.entryPointType = entryPointType;
        this.internalName = internalName;
        this.tierId = tierId;
        this.tierName = tierName;
        this.background = background;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + id + Globals.SPACE + name + Globals.SPACE + entryPointType + Globals.SPACE + internalName + Globals.SPACE + tierId + Globals.SPACE + tierName + Globals.SPACE + background + Globals.CLOSING_SBRACKETS;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEntryPointType() {
        return entryPointType;
    }

    public String getInternalName() {
        return internalName;
    }

    public String getTierId() {
        return tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public String getBackground() {
        return background;
    }
}
