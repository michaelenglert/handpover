package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

import java.util.List;

/**
 * Created by michi on 18.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Backends {
    public String applicationComponentNodeId;
    public String exitPointType;
    public String id;
    public String name;
    public List<ExitPointProperties> properties;
    public String tierId;

    public Backends(){}

    public Backends (String id, String name, String exitPointType, List<ExitPointProperties> properties, String applicationComponentNodeId, String tierId){
        this.id = id;
        this.name = name;
        this.exitPointType = exitPointType;
        this.properties = properties;
        this.applicationComponentNodeId = applicationComponentNodeId;
        this.tierId = tierId;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + applicationComponentNodeId + Globals.SPACE + exitPointType + Globals.SPACE + id + Globals.SPACE + name + Globals.SPACE + properties + Globals.SPACE + tierId + Globals.CLOSING_SBRACKETS;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getExitPointType() {
        return exitPointType;
    }

    public List<ExitPointProperties> getProperties() {
        return properties;
    }

    public String getApplicationComponentNodeId() {
        return applicationComponentNodeId;
    }

    public String getTierId() {
        return tierId;
    }
}
