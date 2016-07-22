package com.vitaliyhtc.autoelectric.lib;

/**
 * Created by VitaliyHTC on 04.07.2016.
 */

public class EIAValuesTable {
    public static String[] exxpArray = new String[]{"E6 (20%)", "E12 (10%)", "E24 (5%)", "E48 (2%)", "E96 (1%)", "E192 (\u22640.5%)"};
    public static String[] exxArray = new String[]{" E6", " E12", " E24", " E48", " E96", "E192"};
    static final double[] e6 = new double[]{1.0, 1.5, 2.2, 3.3, 4.7, 6.8};
    static final double[] e12 = new double[]{1.0, 1.2, 1.5, 1.8, 2.2, 2.7, 3.3, 3.9, 4.7, 5.6, 6.8, 8.2};
    static final double[] e24 = new double[]{1.0, 1.1, 1.2, 1.3, 1.5, 1.6, 1.8, 2.0, 2.2, 2.4, 2.7, 3.0, 3.3, 3.6, 3.9, 4.3, 4.7, 5.1, 5.6, 6.2, 6.8, 7.5, 8.2, 9.1};
    static final double[] e48 = new double[]{1.0, 1.05, 1.1, 1.15, 1.21, 1.27, 1.33, 1.4, 1.47, 1.54, 1.62, 1.69, 1.78, 1.87, 1.96, 2.05, 2.15, 2.26, 2.37, 2.49, 2.61, 2.74, 2.87, 3.01, 3.16, 3.32, 3.48, 3.65, 3.83, 4.02, 4.22, 4.42, 4.64, 4.87, 5.11, 5.36, 5.62, 5.9, 6.19, 6.49, 6.81, 7.15, 7.5, 7.87, 8.25, 8.66, 9.09, 9.53};
    static final double[] e96 = new double[]{1.0, 1.02, 1.05, 1.07, 1.1, 1.13, 1.15, 1.18, 1.21, 1.24, 1.27, 1.3, 1.33, 1.37, 1.4, 1.43, 1.47, 1.5, 1.54, 1.58, 1.62, 1.65, 1.69, 1.74, 1.78, 1.82, 1.87, 1.91, 1.96, 2.0, 2.05, 2.1, 2.15, 2.21, 2.26, 2.32, 2.37, 2.43, 2.49, 2.55, 2.61, 2.67, 2.74, 2.8, 2.87, 2.94, 3.01, 3.09, 3.16, 3.24, 3.32, 3.4, 3.48, 3.57, 3.65, 3.74, 3.83, 3.92, 4.02, 4.12, 4.22, 4.32, 4.42, 4.53, 4.64, 4.75, 4.87, 4.99, 5.11, 5.23, 5.36, 5.49, 5.62, 5.76, 5.9, 6.04, 6.19, 6.34, 6.49, 6.65, 6.81, 6.98, 7.15, 7.32, 7.5, 7.68, 7.87, 8.06, 8.25, 8.45, 8.66, 8.87, 9.09, 9.31, 9.53, 9.76};
    static final double[] e192 = new double[]{1.0, 1.01, 1.02, 1.04, 1.05, 1.06, 1.07, 1.09, 1.1, 1.11, 1.13, 1.14, 1.15, 1.17, 1.18, 1.2, 1.21, 1.23, 1.24, 1.26, 1.27, 1.29, 1.3, 1.32, 1.33, 1.35, 1.37, 1.38, 1.4, 1.42, 1.43, 1.45, 1.47, 1.49, 1.5, 1.52, 1.54, 1.56, 1.58, 1.6, 1.62, 1.64, 1.65, 1.67, 1.69, 1.72, 1.74, 1.76, 1.78, 1.8, 1.82, 1.84, 1.87, 1.89, 1.91, 1.93, 1.96, 1.98, 2.0, 2.03, 2.05, 2.08, 2.1, 2.13, 2.15, 2.18, 2.21, 2.23, 2.26, 2.29, 2.32, 2.34, 2.37, 2.4, 2.43, 2.46, 2.49, 2.52, 2.55, 2.58, 2.61, 2.64, 2.67, 2.71, 2.74, 2.77, 2.8, 2.84, 2.87, 2.91, 2.94, 2.98, 3.01, 3.05, 3.09, 3.12, 3.16, 3.2, 3.24, 3.28, 3.32, 3.36, 3.4, 3.44, 3.48, 3.52, 3.57, 3.61, 3.65, 3.7, 3.74, 3.79, 3.83, 3.88, 3.92, 3.97, 4.02, 4.07, 4.12, 4.17, 4.22, 4.27, 4.32, 4.37, 4.42, 4.48, 4.53, 4.59, 4.64, 4.7, 4.75, 4.81, 4.87, 4.93, 4.99, 5.05, 5.11, 5.17, 5.23, 5.3, 5.36, 5.42, 5.49, 5.56, 5.62, 5.69, 5.76, 5.83, 5.9, 5.97, 6.04, 6.12, 6.19, 6.26, 6.34, 6.42, 6.49, 6.57, 6.65, 6.73, 6.81, 6.9, 6.98, 7.06, 7.15, 7.23, 7.32, 7.41, 7.5, 7.59, 7.68, 7.77, 7.87, 7.96, 8.06, 8.16, 8.25, 8.35, 8.45, 8.56, 8.66, 8.76, 8.87, 8.98, 9.09, 9.2, 9.31, 9.42, 9.53, 9.65, 9.76, 9.88};
    private double[] selectedLine;
    private static int[] ints;

    public double i = 0.0;
    public double j = 0.0;
    public double k = 0.0;
    public double l = 0.0;
    public double m = 0.0;
    public double n = 0.0;
    public double o = 0.0;
    public double p = 0.0;
    public double q = 1.0E9;
    public double r = 1.0E9;
    public double s = 1.0E9;

    public double t;
    public double error1Is;
    public double v;
    public double w;
    public double error2Is;
    public double y;
    public double z;
    public double error3Is;

    public EIAValuesTable(EIAValuesLines ab2) {
        switch (EIAValuesTable.getOrdinal()[ab2.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.selectedLine = e6;
                return;
            }
            case 2: {
                this.selectedLine = e12;
                return;
            }
            case 3: {
                this.selectedLine = e24;
                return;
            }
            case 4: {
                this.selectedLine = e48;
                return;
            }
            case 5: {
                this.selectedLine = e96;
                return;
            }
            case 6: {
                this.selectedLine = e192;
            }
        }
    }

    private double[] a(double[] arrd) {
        double[] arrd2 = new double[2];
        if (arrd[0] < (double)(this.selectedLine.length - 1)) {
            arrd2[0] = arrd[0] + 1.0;
            arrd2[1] = arrd[1];
            return arrd2;
        }
        arrd2[0] = 0.0;
        arrd2[1] = arrd[1] * 10.0;
        return arrd2;
    }

    static int[] getOrdinal() {
        int[] arrn;
        arrn = ints;
        if (arrn != null) {
            return arrn;
        }
        arrn = new int[EIAValuesLines.values().length];
        try {
            arrn[EIAValuesLines.a.ordinal()] = 1;
        }
        catch (NoSuchFieldError error) {}
        try {
            arrn[EIAValuesLines.b.ordinal()] = 2;
        }
        catch (NoSuchFieldError error) {}
        try {
            arrn[EIAValuesLines.c.ordinal()] = 3;
        }
        catch (NoSuchFieldError error) {}
        try {
            arrn[EIAValuesLines.d.ordinal()] = 4;
        }
        catch (NoSuchFieldError error) {}
        try {
            arrn[EIAValuesLines.e.ordinal()] = 5;
        }
        catch (NoSuchFieldError error) {}
        try {
            arrn[EIAValuesLines.f.ordinal()] = 6;
        }
        catch (NoSuchFieldError error) {}
        ints = arrn;
        return arrn;
    }

    private double[] c(double d2) {
        double d3 = d2;
        if (d2 < 1.0) {
            d3 = 1.0;
        }
        d2 = Math.log(d3) / Math.log(10.0);
        d3 = Math.floor(d2);
        return new double[]{Math.floor((d2 - d3) * (double)this.selectedLine.length), Math.pow(10.0, d3)};
    }

    private double[] d(double d2) {
        double[] arrd = this.c(d2);
        double[] arrd2 = this.a(arrd);
        double d3 = (this.selectedLine[(int)arrd[0]] * arrd[1] - d2) / d2;
        if (Math.abs((this.selectedLine[(int)arrd2[0]] * arrd2[1] - d2) / d2) < Math.abs(d3)) {
            return arrd2;
        }
        return arrd;
    }

    private double[] e(double d2) {
        double d3;
        double d4;
        double d5;
        double[] arrd = this.c(d2 / 2.0);
        double d6 = this.selectedLine[(int)arrd[0]] * arrd[1];
        double d7 = 1.0E9;
        double d8 = 0.0;
        double d9 = 0.0;
        do {
            double[] arrd2 = this.d(d2 - d6);
            double d10 = this.selectedLine[(int)arrd2[0]] * arrd2[1];
            d4 = d6 + d10 - d2;
            d5 = d9;
            d3 = d8;
            double d11 = d7;
            if (Math.abs(d4) < Math.abs(d7)) {
                d5 = d10;
                d11 = d4;
                d3 = d6;
            }
            arrd = this.a(arrd);
            d4 = this.selectedLine[(int)arrd[0]] * arrd[1];
            d9 = d5;
            d8 = d3;
            d7 = d11;
            d6 = d4;
        } while (d4 < d2);
        return new double[]{d3, d5};
    }

    private double[] f(double var1) {
        double[] var15 = this.c(var1);
        double var3 = this.selectedLine[(int)var15[0]];
        double var9 = var15[1] * var3;
        double var5 = 1.0E9D;
        var3 = 0.0D;
        double var7 = 0.0D;
        while(true) {
            double var11;
            label19: {
                if(var9 != var1) {
                    double[] var16 = this.d(1.0D / (1.0D / var1 - 1.0D / var9));
                    var11 = this.selectedLine[(int)var16[0]];
                    var11 = var16[1] * var11;
                    if(var11 < 1.0E9D) {
                        double var13 = var9 * var11 / (var9 + var11) - var1;
                        if(Math.abs(var13) < Math.abs(var5)) {
                            var5 = var9;
                            var7 = var13;
                            var3 = var11;
                            break label19;
                        }
                    }
                }
                var9 = var7;
                var7 = var5;
                var5 = var3;
                var3 = var9;
            }
            var15 = this.a(var15);
            var9 = this.selectedLine[(int)var15[0]] * var15[1];
            if(var9 >= 2.0D * var1) {
                return new double[]{var5, var3};
            }
            var11 = var7;
            var7 = var3;
            var3 = var5;
            var5 = var11;
        }
    }

    public void a(double d2) {
        double d3 = Math.round(Math.pow(10.0, Math.floor(Math.log(Math.sqrt(1.0E9 * d2)) / Math.log(10.0))));
        this.i = 0.0;
        this.j = 0.0;
        this.k = 0.0;
        this.l = 0.0;
        this.m = 0.0;
        this.n = 0.0;
        this.o = 0.0;
        this.p = 0.0;
        this.q = 1.0E9;
        this.r = 1.0E9;
        this.s = 1.0E9;
        double[] arrd = this.selectedLine;
        int n2 = arrd.length;
        int n3 = 0;
        do {
            double d4;
            double[] arrd2;
            double d5;
            if (n3 >= n2) {
                this.i = (double)Math.round(100.0 * this.i) / 100.0;
                this.j = (double)Math.round(100.0 * this.j) / 100.0;
                this.k = (double)Math.round(100.0 * this.k) / 100.0;
                this.l = (double)Math.round(100.0 * this.l) / 100.0;
                this.m = (double)Math.round(100.0 * this.m) / 100.0;
                this.n = (double)Math.round(100.0 * this.n) / 100.0;
                this.o = (double)Math.round(100.0 * this.o) / 100.0;
                this.p = (double)Math.round(100.0 * this.p) / 100.0;
                this.q = 100.0 * (this.q - 1.0);
                this.r = 100.0 * (this.r - 1.0);
                this.s = 100.0 * (this.s - 1.0);
                return;
            }
            double d6 = Math.round(arrd[n3] * d3);
            double d7 = d6 / (d5 = this.selectedLine[(int)(arrd2 = this.d(d4 = d6 / d2))[0]] * arrd2[1]);
            d7 = d7 > d2 ? (d7 /= d2) : d2 / d7;
            if (d7 < this.q) {
                this.q = d7;
                this.i = d6;
                this.j = d5;
            }
            d7 = (d7 = d6 / ((arrd2 = this.e(d4))[0] + arrd2[1])) > d2 ? (d7 /= d2) : d2 / d7;
            if (d7 < this.r) {
                this.r = d7;
                this.k = d6;
                this.l = arrd2[0];
                this.m = arrd2[1];
            }
            d7 = (d7 = ((arrd2 = this.f(d4))[0] + arrd2[1]) * d6 / (arrd2[0] * arrd2[1])) > d2 ? (d7 /= d2) : d2 / d7;
            if (d7 < this.s) {
                this.s = d7;
                this.n = d6;
                this.o = arrd2[0];
                this.p = arrd2[1];
            }
            ++n3;
        } while (true);
    }

    public void selectEIAValuesLine(int n2) {
        switch (n2) {
            default: {
                return;
            }
            case 0: {
                this.selectedLine = e6;
                return;
            }
            case 1: {
                this.selectedLine = e12;
                return;
            }
            case 2: {
                this.selectedLine = e24;
                return;
            }
            case 3: {
                this.selectedLine = e48;
                return;
            }
            case 4: {
                this.selectedLine = e96;
                return;
            }
            case 5: {
                this.selectedLine = e192;
            }
        }
    }

    public void selectEIAValuesLine(EIAValuesLines ab2) {
        switch (EIAValuesTable.getOrdinal()[ab2.ordinal()]) {
            default: {
                return;
            }
            case 1: {
                this.selectedLine = e6;
                return;
            }
            case 2: {
                this.selectedLine = e12;
                return;
            }
            case 3: {
                this.selectedLine = e24;
                return;
            }
            case 4: {
                this.selectedLine = e48;
                return;
            }
            case 5: {
                this.selectedLine = e96;
                return;
            }
            case 6: {
                this.selectedLine = e192;
            }
        }
    }

    public void calcError(double d2) {
        double[] arrd = this.d(d2);
        double d3 = this.selectedLine[(int)arrd[0]];
        this.t = (double)Math.round(arrd[1] * (d3 * 100.0)) / 100.0;
        this.error1Is = (this.t - d2) / d2;
        this.error1Is *= 100.0;
        arrd = this.e(d2);
        this.v = (double)Math.round(arrd[0] * 100.0) / 100.0;
        this.w = (double)Math.round(arrd[1] * 100.0) / 100.0;
        this.error2Is = (this.v + this.w - d2) / d2;
        this.error2Is *= 100.0;
        arrd = this.f(d2);
        this.y = (double)Math.round(arrd[0] * 100.0) / 100.0;
        this.z = (double)Math.round(arrd[1] * 100.0) / 100.0;
        this.error3Is = (this.y * this.z / (this.y + this.z) - d2) / d2;
        this.error3Is *= 100.0;
    }
}
