package Lib;

public class Employee extends People {
   private String company;
   private double salary;
   
   public String getCompany(){
    return this.company;
   }
    
   public void setCompany(String company){
    this.company = company;
   }

   public double getSalary(){
    return this.salary;
   }

   public void setSalary(double salary){
    this.salary = salary;
   }

   public boolean Taxpay(){
    if (salary*12>200000) 
   return true;
   else
   return false;
   }

   public void Show(){
    return "I'm " +this.getName()+ "\n I work for"+this.getCompany();
   }
}
