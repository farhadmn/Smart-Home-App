package de.design_muc.SmartHome.SenSoModClasses.ContextDescription;

import java.util.List;

public class ContextDescription {

    protected String name;
    List<String> tasks;


    public ContextDescription(String name, List<String> tasks) {
        this.name = name;
        this.tasks = tasks;
    }
}
