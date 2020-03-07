package com.example.example.item;

public class Activity_item {
    private String activty_Name;
    private Class activity_Class;

    public Activity_item(String activty_Name, Class activity_Class) {
        this.activty_Name = activty_Name;
        this.activity_Class = activity_Class;
    }

    public String getActivty_Name() {
        return activty_Name;
    }

    public void setActivty_Name(String activty_Name) {
        this.activty_Name = activty_Name;
    }

    public Class getActivity_Class() {
        return activity_Class;
    }

    public void setActivity_Class(Class activity_Class) {
        this.activity_Class = activity_Class;
    }
}
