package ohtu;

public class Submission {
    private String student_number;
    private int week;
    private int hours;
    private boolean a1;
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
    private int tehtYht;
    
    public int getWeek() {
	return this.week;
    }
    
    public void setWeek(int viikko) {
	this.week = viikko;
    }
    
    public int getTehtYht() {
        int Yht=0;
        if (a1==true) Yht++;
        if (a2==true) Yht++;        
        if (a3==true) Yht++;
        if (a4==true) Yht++;
        if (a5==true) Yht++;
        if (a6==true) Yht++;
        if (a7==true) Yht++;
        if (a8==true) Yht++;
        if (a9==true) Yht++;
        if (a10==true) Yht++;
        if (a11==true) Yht++;
        if (a12==true) Yht++;
        if (a13==true) Yht++;
        if (a14==true) Yht++;
        if (a15==true) Yht++;
        if (a16==true) Yht++;
    
	return Yht;
    }
    
    public String getTehdyt() {
        String tasks="";;
        if (a1==true) tasks="1";
        if (a2==true) tasks=tasks+" 2";
        if (a3==true) tasks=tasks+" 3";
        if (a4==true) tasks=tasks+" 4";
        if (a5==true) tasks=tasks+" 5";
        if (a6==true) tasks=tasks+" 6";
        if (a7==true) tasks=tasks+" 7";
        if (a8==true) tasks=tasks+" 8";
        if (a9==true) tasks=tasks+" 9";
        if (a10==true) tasks=tasks+" 10";
        if (a11==true) tasks=tasks+" 11";
        if (a12==true) tasks=tasks+" 12";
        if (a13==true) tasks=tasks+" 13";
        if (a14==true) tasks=tasks+" 14";
        if (a15==true) tasks=tasks+" 15";
        if (a16==true) tasks=tasks+" 16";
        return tasks;
    }
    
    public void setTehtYht(int numberAssign) {
	this.tehtYht = numberAssign;
    }
    
    public int getHours() {
	return this.hours;
    }
    
    public void setHours(int time) {
	this.hours = time;
    }    
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    @Override
    public String toString() {
        return "viikko " +week+ ": tehtyjä tehtäviä yhteensä: " +this.getTehtYht()+ ", aikaa kului " +hours + " tuntia, tehdyt tehtävät: " +this.getTehdyt();
    }
  // viikko 1: tehtyjä tehtäviä yhteensä: 9, aikaa kului 3 tuntia, tehdyt tehtävät 
}