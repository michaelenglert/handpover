package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 18.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class ExitPointProperties {
    public String id;
    public String name;
    public String value;

    public ExitPointProperties(){}

    public ExitPointProperties (String id, String name, String value){
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + id + Globals.SPACE + name + Globals.SPACE + value + Globals.CLOSING_SBRACKETS;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
