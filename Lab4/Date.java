public class Date{
    private int day;
    private int month;
    private int year;

    public Date(int day, int month,int year){
        this.day=day;
        this.month=month;
        this.year=year;
    }

    public void setDay(int day){
        if (day<=31 && day!=0) {
            this.day = day;
        }else System.out.println(this.day);
    }

    public void setMonth(int month){
        if (month<=12 && month!=0) {
            this.month=month;
        }else System.out.println(this.month);
    }

    public void setYear(int year){
        this.year=year;
    }

    public int getDay(){
        return this.day;
    }

    public int getMonth(){
        return this.month;
    }

    public int getYear(){
        return this.year+543;
    }

}
