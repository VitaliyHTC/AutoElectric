package com.vitaliyhtc.autoelectric.lib;

/**
 * Created by VitaliyHTC on 04.07.2016.
 */
public enum EIAValuesLines {
    a("E6", 0),
    b("E12", 1),
    c("E24", 2),
    d("E48", 3),
    e("E96", 4),
    f("E192", 5);

    private static final EIAValuesLines[] g = new EIAValuesLines[]{a, b, c, d, e, f};

    EIAValuesLines(String var1, int var2) {}
}
