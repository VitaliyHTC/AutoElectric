package com.vitaliyhtc.autoelectric;

public class MainListItem {
    private int icon;
    private String title;
    private MainListItemType mainListItemType;
    private Class targetActivityClass = MainActivity.class;
    private String targetSource;

    public MainListItem(int icon, String title, MainListItemType mainListItemType, Class clazz, String targetSource) {
        this.icon = icon;
        this.title = title;
        this.mainListItemType = mainListItemType;
        this.targetActivityClass = clazz;
        this.targetSource = targetSource;
    }

    public int getIcon() {
        return icon;
    }
    public String getTitle() {
        return title;
    }
    public MainListItemType getMainListItemType() {
        return mainListItemType;
    }
    public Class getTargetActivityClass() {
        return targetActivityClass;
    }
    public String getTargetSource() {
        return targetSource;
    }
}
