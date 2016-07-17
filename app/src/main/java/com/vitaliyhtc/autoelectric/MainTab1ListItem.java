package com.vitaliyhtc.autoelectric;

/**
 * Created by VitaliyHTC on 01.07.2016.
 */
public class MainTab1ListItem {
    private int icon;
    private String title;
    private Class targetActivityClass = MainActivity.class;

    public MainTab1ListItem(int icon, String title, Class clazz) {
        this.icon = icon;
        this.title = title;
        this.targetActivityClass = clazz;
    }

    public int getIcon() {
        return icon;
    }
    public String getTitle() {
        return title;
    }
    public Class getTargetActivityClass() {
        return targetActivityClass;
    }
}
