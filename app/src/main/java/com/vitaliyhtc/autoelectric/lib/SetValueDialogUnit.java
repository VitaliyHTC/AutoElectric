package com.vitaliyhtc.autoelectric.lib;

import java.util.ArrayList;

public final class SetValueDialogUnit {
    final SetValueDialog setValueDialog;
    private ArrayList unitsArray;
    private String compUnit;

    public SetValueDialogUnit(SetValueDialog setValueDialog, String string) {
        this.setValueDialog = setValueDialog;
        this.unitsArray = new ArrayList();
        this.compUnit = string;
        Boolean bl2 = false;
        if (string.equals("s")) {
            bl2 = true;
        }
        if(!string.equals("") && !string.equals("RC")) {
            if(string.startsWith("°C")) {
                if(!SetValueDialog.a(setValueDialog).getBoolean("Unit_tempC", true) && string.equals("°C")) {
                    this.unitsArray.add("°F");
                } else {
                    this.unitsArray.add(string);
                }
            } else if(!string.startsWith("dB") && !string.equals("%") && !string.equals("bit") && !string.equals("°")) {
                if(!SetValueDialog.a(setValueDialog).getBoolean("Unit_SI", true) && string.equals("m")) {
                    this.unitsArray.add("in");
                    this.unitsArray.add("ft");
                    this.unitsArray.add("mi");
                } else if(bl2.booleanValue()) {
                    this.unitsArray.add("p" + string);
                    this.unitsArray.add("n" + string);
                    this.unitsArray.add("μ" + string);
                    this.unitsArray.add("m" + string);
                    this.unitsArray.add(string);
                } else {
                    this.unitsArray.add("p" + string);
                    this.unitsArray.add("n" + string);
                    this.unitsArray.add("μ" + string);
                    this.unitsArray.add("m" + string);
                    this.unitsArray.add(string);
                    this.unitsArray.add("k" + string);
                    this.unitsArray.add("M" + string);
                    this.unitsArray.add("G" + string);
                }
            } else {
                this.unitsArray.add(string);
            }
        }
    }

    public double a(int n2) {
        String string = (String)this.unitsArray.get(n2);
        if (string.equals("m") || string.equals("m/s")) {
            return 1.0;
        }
        if (!SetValueDialog.a(this.setValueDialog).getBoolean("Unit_SI", true) && this.compUnit.equals("m")) {
            switch (string.charAt(0)) {
                default: {
                    return 1.0;
                }
                case 'i': {
                    return 0.0254;
                }
                case 'f': {
                    return 0.3048;
                }
                case 'm': {
                    return 1609.344;
                }
            }
        }
        switch (string.charAt(0)) {
            default: {
                return 1.0;
            }
            case 'p': {
                return 1.0E-12;
            }
            case 'n': {
                return 1.0E-9;
            }
            case '\u03bc': {
                return 1.0E-6;
            }
            case 'm': {
                return 0.001;
            }
            case 'k': {
                return 1000.0;
            }
            case 'M': {
                return 1000000.0;
            }
            case 'G': {
                return 1.0E9;
            }
        }
    }

    public int a(char c2) {
        String string;
        if (c2 == '\u0000') {
            string = this.compUnit;
        }else{
            string = c2 + this.compUnit;
        }
        return this.unitsArray.indexOf(string);
    }

    public int a(String string) {
        return this.unitsArray.indexOf(string);
    }

    public ArrayList a() {
        return this.unitsArray;
    }

    public int b() {
        return this.unitsArray.size();
    }
}
