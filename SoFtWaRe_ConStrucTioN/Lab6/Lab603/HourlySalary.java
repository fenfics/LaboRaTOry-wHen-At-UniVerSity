public class HourlySalary extends Employees {
private double hpay;
private double hwork;
private double e=3300.00;
    public HourlySalary(String f , String l, String s,double p,double w){
        super(f,l,s);
        this.hpay=p;
        this.hwork=w;
       
    }

    public double HPay(){
        return this.hpay;
    }

    public double HWork(){
        return this.hwork;
    }

   
    public double earnings() {
       return this.e;
    }

    public String toString(){
        return "hourly employee: "+super.toString()+"\nhourly pay: $ "+String.format("%.2f", this.hpay)
        +"; hours worked: "+String.format("%.2f", this.hwork);

    }

}
