package ohtu;

import java.lang.reflect.Field;

public class Submission {
    private String student_number;
    private String week;
    private String hours;
    private int total;
    
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
    private boolean a17;
    private boolean a18;
    private boolean a19;
    private boolean a20;
    private boolean a21;
    
    public Submission()
    {
        this.total = 0;
    }
    
    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }
    
    public int get_hours()
    {
        return Integer.parseInt(this.hours);
    }
    
    public int get_total_exe()
    {
        return this.total;
    }

    @Override
    public String toString() {
        String ex_nums = "";
        this.total = 0;
        Field[] fields = this.getClass().getDeclaredFields();
        
        for(int i = 0;i < fields.length;i ++)
        {
            if(fields[i].getName().matches("a*[0-9]*") && this.get_field_value(fields[i], this))
            {
                ex_nums = ex_nums + fields[i].getName().substring(1) + " ";
                this.total ++;
            }
        }
        return "\tviikko " + week + ": tehtyjä tehtäviä yhteensä: " + total + ", aikaa kului " + hours + " tuntia, tehdyt tehtävät: " + ex_nums;
    }
    
    private boolean get_field_value(Field field, Object o)
    {
        try {
            return (Boolean)field.get(o);
        } catch(Exception e) {
            return false;
        }
    }
}
