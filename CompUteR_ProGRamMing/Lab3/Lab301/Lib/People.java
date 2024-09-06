package Lib;

public class People {
    private String firstname;
    private String lastname;
    protected MyDate birthdate = new MyDate();

    public String getFirstName(){
        return this.firstname;
    }

    public void setFirstName(String first){
        this.firstname = first;
    }

    public String getLastName(){
        return this.lastname;
    }

    public void setLastName(String last){
        this.lastname = last;
    }

    public String getName(){
        return this.getFirstName()+""+this.getLastName();
    }

    public void setName(String first, String last){
        this.setFirstName(first);
        this.setLastName(last);
    }

    public MyDate getBirthdate(){
        return this.birthdate;
    }

    public void setBirthdate(int day,int month,int year){
        this.birthdate.setDate(day, month, year);
    }

    public String toString(){
        return "My name is"+this.getName()+".\nI was born on"+this.getBirthdate()+".";
    }
}
