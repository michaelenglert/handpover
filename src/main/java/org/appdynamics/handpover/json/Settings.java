package org.appdynamics.handpover.json;

import org.appdynamics.handpover.config.Globals;

/**
 * Created by michi on 03.09.16.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class Settings {
    public String description;
    public String name;
    public String scope;
    public String updateable;
    public String value;

    public Settings() {

    }
    public Settings(String description, String name, String scope, String updateable, String value) {
        this.description = description;
        this.name = name;
        this.scope = scope;
        this.updateable = updateable;
        this.value = value;
    }

    public String toString() {
        return  Globals.OPENING_SBRACKETS + description + Globals.SPACE + name + Globals.SPACE + scope + Globals.SPACE + updateable + Globals.SPACE + value + Globals.CLOSING_SBRACKETS;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String getScope() {
        return scope;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdateable() {
        return updateable;
    }
}
