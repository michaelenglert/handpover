package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 18.09.16.
 */
public class Tiers {
    public String id;
    public String name;
    public String type;
    public String agentType;
    public String numberOfNodes;
    public String description;

    public Tiers(){}

    public Tiers (String id, String name, String type, String agentType, String numberOfNodes, String description){
        this.id = id;
        this.name = name;
        this.type = type;
        this.agentType = agentType;
        this.numberOfNodes = numberOfNodes;
        this.description = description;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + id + Globals.SPACE + name + Globals.SPACE + type + Globals.SPACE + agentType + Globals.SPACE + numberOfNodes + Globals.SPACE + description + Globals.CLOSING_SBRACKETS;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getAgentType() {
        return agentType;
    }

    public String getNumberOfNodes() {
        return numberOfNodes;
    }

    public String getDescription() {
        return description;
    }
}
