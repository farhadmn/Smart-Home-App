package de.design_muc.SmartHome.SenSoModClasses.Sensoren;

import java.util.ArrayList;

public class ContextDescription {

    protected String name;
    private ArrayList<String> todos;
    private static ContextDescription instance = null;

    public ContextDescription(String name) {

        this.name = name;
        todos = new ArrayList<String>();

    }

    public static ContextDescription getInstance() {
        if (instance == null)
            instance = new ContextDescription("ContextDescription");
        return instance;
    }

    public void setTodo(String todo) {
        todos.add(todo);

    }

    public String getTodo(int pos) {
        String todo;
        todo = todos.get(pos);

        return todo;
    }

    public int getSize() {

        int i = todos.size();
        return i;
    }

    public void deleteEintrag(int pos) {

        todos.remove(pos);
    }

    public ArrayList<String> getTodos() {
        return todos;
    }


}
// Use IDE to generate toString and equals methods

