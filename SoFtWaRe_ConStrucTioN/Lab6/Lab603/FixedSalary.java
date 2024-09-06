public class FixedSalary extends Employees{
private double e;
    public FixedSalary(String f , String l, String s,double e){
        super(f,l,s);
        this.e=e;
    }
    
    public double earnings() {
        return this.e;
    }

    public String toString(){
        return "Fixed salary employee: "+super.toString()+"\nmonthly salary: $"+String.format("%.2f",this.e);

    }

}