public class Undergrad extends Student {
    protected String year;

    public Undergrad(int i ,String n,double g,String year){
        super(i , n ,g);
        this.year=year;
    }

    public void setYear(String year){
        this.year=year;
    }

    public String getYear(){
        return this.year;
    }

    public String toString(){
        return "Undergraduate Student:\nID: "+this.getId()+"\nName: "+this.getName()+"\nGPA: "+this.getGPA()+"\nYear: "+this.getYear();
    }
}
