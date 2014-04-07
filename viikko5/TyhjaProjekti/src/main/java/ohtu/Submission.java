package ohtu;

import java.util.ArrayList;

public class Submission {
    private String student_number;
    private int hours;
    private int week;
    private Object a1;
    private boolean a2;
    private boolean a3;
    private boolean a4;
    private boolean a5;
    private boolean a6;
    private boolean a7;
    private boolean a8;
    private boolean a9;
    private boolean a10;
    private boolean a11;
    private boolean a12;
    private boolean a13;
    private boolean a14;
    private boolean a15;
    private boolean a16;
    private boolean a17;
    private boolean a18;
    private boolean a19;
    private boolean a20;
    private boolean a21;
    
    private ArrayList tehdyt = new ArrayList();

    public Object getA1() {
        return a1;
    }

    public void setA1(boolean a1) {
        this.a1 = a1;
        if (a1 = true) {
            tehdyt.add(a1);
        }
    }

    public boolean isA2() {
        return a2;
    }

    public void setA2(boolean a2) {
        this.a2 = a2;
        if (a2 = true) {
            tehdyt.add(a2);
        }
    }

    public boolean isA3() {
        return a3;
    }

    public void setA3(boolean a3) {
        this.a3 = a3;
        if (a3 = true) {
            tehdyt.add(a3);
        }
    }

    public boolean isA4() {
        return a4;
    }

    public void setA4(boolean a4) {
        this.a4 = a4;
        if (a4 = true) {
            tehdyt.add(a4);
        }
    }

    public boolean isA5() {
        return a5;
    }

    public void setA5(boolean a5) {
        this.a5 = a5;
        if (a5 = true) {
            tehdyt.add(a5);
        }
    }

    public boolean isA6() {
        return a6;
    }

    public void setA6(boolean a6) {
        this.a6 = a6;
        if (a6 = true) {
            tehdyt.add(a6);
        }
    }

    public boolean isA7() {
        return a7;
    }

    public void setA7(boolean a7) {
        this.a7 = a7;
        if (a7 = true) {
            tehdyt.add(a7);
        }
    }

    public boolean isA8() {
        return a8;
    }

    public void setA8(boolean a8) {
        this.a8 = a8;
        if (a8 = true) {
            tehdyt.add(a8);
        }
    }

    public boolean isA9() {
        return a9;
    }

    public void setA9(boolean a9) {
        this.a9 = a9;
        if (a9 = true) {
            tehdyt.add(a9);
        }
    }

    public boolean isA10() {
        return a10;
    }

    public void setA10(boolean a10) {
        this.a10 = a10;
        if (a10 = true) {
            tehdyt.add(a10);
        }
    }

    public boolean isA11() {
        return a11;
    }

    public void setA11(boolean a11) {
        this.a11 = a11;
        if (a11 = true) {
            tehdyt.add(a11);
        }
    }

    public boolean isA12() {
        return a12;
    }

    public void setA12(boolean a12) {
        this.a12 = a12;
        if (a12 = true) {
            tehdyt.add(a12);
        }
    }

    public boolean isA13() {
        return a13;
    }

    public void setA13(boolean a13) {
        this.a13 = a13;
        if (a13 = true) {
            tehdyt.add(a13);
        }
    }

    public boolean isA14() {
        return a14;
    }

    public void setA14(boolean a14) {
        this.a14 = a14;
        if (a14 = true) {
            tehdyt.add(a14);
        }
    }

    public boolean isA15() {
        return a15;
    }

    public void setA15(boolean a15) {
        this.a15 = a15;
        if (a15 = true) {
            tehdyt.add(a15);
        }
    }

    public boolean isA16() {
        return a16;
    }

    public void setA16(boolean a16) {
        this.a16 = a16;
        if (a16 = true) {
            tehdyt.add(a16);
        }
    }

    public boolean isA17() {
        return a17;
    }

    public void setA17(boolean a17) {
        this.a17 = a17;
        if (a17 = true) {
            tehdyt.add(a17);
        }
    }

    public boolean isA18() {
        return a18;
    }

    public void setA18(boolean a18) {
        this.a18 = a18;
        if (a18 = true) {
            tehdyt.add(a18);
        }
    }

    public boolean isA19() {
        return a19;
    }

    public void setA19(boolean a19) {
        this.a19 = a19;
        if (a19 = true) {
            tehdyt.add(a19);
        }
    }

    public boolean isA20() {
        return a20;
    }

    public void setA20(boolean a20) {
        this.a20 = a20;
        if (a20 = true) {
            tehdyt.add(a20);
        }
    }

    public boolean isA21() {
        return a21;
    }

    public void setA21(boolean a21) {
        this.a21 = a21;
        if (a21 = true) {
            tehdyt.add(a21);
        }
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        String tehty = tehdyt();
        
        return "viikko "+week+": "+"tehtyjä tehtäviä yhteensä: "+tehdyt.size()+", aikaa kului "+hours+" tuntia, tehdyt tehtävät: "+tehty;
    }

    private String tehdyt() {
        String tehty = "";
        for (int i = 0; i < tehdyt.size(); i++) {
            tehty = tehty+" "+i;
        }
        
        return tehty;
    }
    
}