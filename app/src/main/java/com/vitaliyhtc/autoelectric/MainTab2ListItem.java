package com.vitaliyhtc.autoelectric;

/**
 * Created by VitaliyHTC on 12.07.2016.
 */
public class MainTab2ListItem {
    private int icon;
    private String title;
    private String targetSource;

    public MainTab2ListItem(int icon, String title, String targetSource) {
        this.icon = icon;
        this.title = title;
        this.targetSource = targetSource;
    }

    public int getIcon() {
        return icon;
    }
    public String getTitle() {
        return title;
    }
    public String getTargetSource() {
        return targetSource;
    }
}
