package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Apps {
    public String description;
    public String id;
    public String name;

    public Apps() {
    }

    public Apps(String description, String id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + description + Globals.SPACE + id + Globals.SPACE + name + Globals.CLOSING_SBRACKETS;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
