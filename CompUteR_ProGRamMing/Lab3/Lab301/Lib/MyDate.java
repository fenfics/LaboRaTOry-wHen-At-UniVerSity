package Lib;

public class MyDate {
    private int day;
    private int month;
    private int year;

    public int getDay(){
        return this.day;
    }

    public void setDay(int day){
        if (day>0 && day<=31) 
        this.day=day;
        else
        System.out.println("cant set day");
    }

    public int getMonth(){
        return this.month;
    }

    public void setMonth(int month){
        if (month>0 && month<=12) 
        this.month=month;
        else
        System.out.println("cant set month");
    }

    public int getYear(){
        return this.year;
    }

    public void setYear(int year){
        if (year>0 ) 
        this.year=year;
        else
        System.out.println("cant set year");
    }

    public void setDate(int day,int month,int year){
        this.setDay(day);
        this.setMonth(month);
        this.setYear(year);
    }

    public String toString(){
        String y = this.getDay()+"/"+this.getMonth()+"/"+this.getYear();
        return y;
    }
    
}
