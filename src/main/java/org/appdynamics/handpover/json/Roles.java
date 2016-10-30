package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Roles {
    public int id;
    public int version;
    public String name;
    public Boolean nameUnique;
    public String description;
    public String displayName;

    public Roles(){
    }

    public Roles(int id, int version, String name, Boolean nameUnique, String description, String displayName) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.nameUnique = nameUnique;
        this.description = description;
        this.displayName = displayName;
    }

    public String toString() {
        return Globals.OPENING_SBRACKETS + id + Globals.SPACE + version + Globals.SPACE + name + Globals.SPACE + nameUnique + Globals.SPACE + description + Globals.SPACE + displayName + Globals.CLOSING_SBRACKETS;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public Boolean getNameUnique() {
        return nameUnique;
    }

    public String getDescription() {
        return description;
    }

    public String getDisplayName() {
        return displayName;
    }
}
