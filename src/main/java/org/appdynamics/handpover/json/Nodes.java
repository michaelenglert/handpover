package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 18.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Nodes {
    public String agentType;
    public String appAgentPresent;
    public String appAgentVersion;
    public String id;
    public String ipAddresses;
    public String machineAgentPresent;
    public String machineAgentVersion;
    public String machineId;
    public String machineName;
    public String machineOSType;
    public String name;
    public String nodeUniqueLocalId;
    public String tierId;
    public String tierName;
    public String type;

    public Nodes(){}

    public Nodes (String agentType, String appAgentPresent, String appAgentVersion, String id, String ipAddresses, String machineAgentPresent, String machineAgentVersion, String machineId, String machineName, String machineOSType, String name, String nodeUniqueLocalId, String tierId, String tierName, String type){
        this.agentType = agentType;
        this.appAgentPresent = appAgentPresent;
        this.appAgentVersion = appAgentVersion;
        this.id = id;
        this.ipAddresses = ipAddresses;
        this.machineAgentPresent = machineAgentPresent;
        this.machineAgentVersion = machineAgentVersion;
        this.machineId = machineId;
        this.machineName = machineName;
        this.machineOSType = machineOSType;
        this.name = name;
        this.nodeUniqueLocalId = nodeUniqueLocalId;
        this.tierId = tierId;
        this.tierName = tierName;
        this.type = type;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + agentType + Globals.SPACE + appAgentPresent +  Globals.SPACE + appAgentVersion +  Globals.SPACE + id +  Globals.SPACE + ipAddresses +  Globals.SPACE + machineAgentPresent +  Globals.SPACE + machineAgentVersion +  Globals.SPACE + machineId +  Globals.SPACE + machineName +  Globals.SPACE + machineOSType +  Globals.SPACE + name +  Globals.SPACE + nodeUniqueLocalId +  Globals.SPACE + tierId +  Globals.SPACE + tierName +  Globals.SPACE + type + Globals.CLOSING_SBRACKETS;
    }

    public String getAgentType() {
        return agentType;
    }

    public String getAppAgentPresent() {
        return appAgentPresent;
    }

    public String getAppAgentVersion() {
        return appAgentVersion;
    }

    public String getId() {
        return id;
    }

    public String getIpAddresses() {
        return ipAddresses;
    }

    public String getMachineAgentPresent() {
        return machineAgentPresent;
    }

    public String getMachineAgentVersion() {
        return machineAgentVersion;
    }

    public String getMachineId() {
        return machineId;
    }

    public String getMachineName() {
        return machineName;
    }

    public String getMachineOSType() {
        return machineOSType;
    }

    public String getName() {
        return name;
    }

    public String getNodeUniqueLocalId() {
        return nodeUniqueLocalId;
    }

    public String getTierId() {
        return tierId;
    }

    public String getTierName() {
        return tierName;
    }

    public String getType() {
        return type;
    }
}
