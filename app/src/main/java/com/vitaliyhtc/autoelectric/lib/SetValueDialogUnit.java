package com.vitaliyhtc.autoelectric.lib;

import java.util.ArrayList;

public final class SetValueDialogUnit {
    private final SetValueDialog setValueDialog;
    private ArrayList unitsArraySymbols;
    private String compUnit;

    public SetValueDialogUnit(SetValueDialog setValueDialog, String string) {
        this.setValueDialog = setValueDialog;
        this.unitsArraySymbols = new ArrayList();
        this.compUnit = string;
        Boolean bl2 = false;
        if (string.equals("s")) {
            bl2 = true;
        }
        if(!string.equals("") && !string.equals("RC")) {
            if(string.startsWith("°C")) {
                if(!SetValueDialog.getSharedPreferences(setValueDialog).getBoolean("Unit_tempC", true) && string.equals("°C")) {
                    this.unitsArraySymbols.add("°F");
                } else {
                    this.unitsArraySymbols.add(string);
                }
            } else if(!string.startsWith("dB") && !string.equals("%") && !string.equals("bit") && !string.equals("°")) {
                if(!SetValueDialog.getSharedPreferences(setValueDialog).getBoolean("Unit_SI", true) && string.equals("m")) {
                    this.unitsArraySymbols.add("in");
                    this.unitsArraySymbols.add("ft");
                    this.unitsArraySymbols.add("mi");
                } else if(bl2.booleanValue()) {
                    this.unitsArraySymbols.add("p" + string);
                    this.unitsArraySymbols.add("n" + string);
                    this.unitsArraySymbols.add("μ" + string);
                    this.unitsArraySymbols.add("m" + string);
                    this.unitsArraySymbols.add(string);
                } else {
                    this.unitsArraySymbols.add("p" + string);
                    this.unitsArraySymbols.add("n" + string);
                    this.unitsArraySymbols.add("μ" + string);
                    this.unitsArraySymbols.add("m" + string);
                    this.unitsArraySymbols.add(string);
                    this.unitsArraySymbols.add("k" + string);
                    this.unitsArraySymbols.add("M" + string);
                    this.unitsArraySymbols.add("G" + string);
                }
            } else {
                this.unitsArraySymbols.add(string);
            }
        }
    }

    public double getUnitMultiplier(int n2) {
        String string = (String)this.unitsArraySymbols.get(n2);
        if (string.equals("m") || string.equals("m/s")) {
            return 1.0;
        }
        if (!SetValueDialog.getSharedPreferences(this.setValueDialog).getBoolean("Unit_SI", true) && this.compUnit.equals("m")) {
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

    public int getCompUnitIndex(char c2) {
        String string;
        if (c2 == '\u0000') {
            string = this.compUnit;
        }else{
            string = c2 + this.compUnit;
        }
        return this.unitsArraySymbols.indexOf(string);
    }

    public int getUnitSymbolIndex(String string) {
        return this.unitsArraySymbols.indexOf(string);
    }

    public ArrayList getUnitsArraySymbols() {
        return this.unitsArraySymbols;
    }

    public int getUnitsArraySymbolsSize() {
        return this.unitsArraySymbols.size();
    }
}
