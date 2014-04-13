package ohtu;

public class Submission {

    private String student_number;
    private String week;
    private String hours;
    private String a1;
    private String a2;
    private String a3;
    private String a4;
    private String a5;
    private String a6;
    private String a7;
    private String a8;
    private String a9;
    private String a10;
    private String a11;
    private String a12;
    private String a13;
    private String a14;
    private String a15;
    private String a16;
    private String a17;
    private String a18;
    private String a19;
    private String a20;
    private String a21;
    private String tehdytTehtavat = "";
    int total = 0;

    public int getTotal() {
        this.total = findTotalNumberOfSubmissions();
        return this.total;
    }
    
    public String getA1() {
        return this.a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }

    public String getA5() {
        return a5;
    }

    public String getA6() {
        return a6;
    }

    public String getA7() {
        return a7;
    }

    public String getA8() {
        return a8;
    }

    public String getA9() {
        return a9;
    }

    public String getA10() {
        return a10;
    }

    public String getA11() {
        return a11;
    }

    public String getA12() {
        return this.a12;
    }

    public String getA13() {
        return a13;
    }

    public String getA14() {
        return a14;
    }

    public String getA15() {
        return a15;
    }

    public String getA16() {
        return a16;
    }

    public String getA17() {
        return a17;
    }

    public String getA18() {
        return a18;
    }

    public String getA19() {
        return a19;
    }

    public String getA20() {
        return a20;
    }

    public String getA21() {
        return a21;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
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
        //return "viikko " + getWeek() + ": tehtyjä tehtäviä yhteensä: ";
        return "Viikko " + getWeek() 
                + ": tehtyjä tehtäviä yhteensä: " + this.total 
                + ", aikaa kului " + getHours() + " tuntia, "
                + "tehdyt tehtävät:" + tehdytTehtavat;
    }

    private int findTotalNumberOfSubmissions() {
        String[] tehtavat = {a1, a2, a3, a4, a5, a6, a7, a8, a9, a10, a11, a12, a13, a14, a15, a16, a17, a18, a19, a20, a21};
        
        int tehtava = 1;
        for (String string : tehtavat) {
            if (string != null) {
                    total++;
                    tehdytTehtavat = tehdytTehtavat + " " + tehtava;
                    tehtava++;
            }
        }
        return total;
    }

}
