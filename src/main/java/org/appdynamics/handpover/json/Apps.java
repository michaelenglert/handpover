package org.appdynamics.handpover.json;

/**
 * Created by michi on 03.09.16.
 */
public class Apps {
    public String description;
    public int id;
    public String name;

    public Apps() {
    }

    public Apps(String description, int id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return  "[" + description + " " + id + " " + name + "]";
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
