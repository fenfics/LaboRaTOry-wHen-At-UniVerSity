public class CommissionPay extends Employees {
private double r;
private double e;
    public CommissionPay(String f , String l, String s,double e,double r){
        super(f,l,s);
        this.e=e;
        this.r=r;
    }

    public double earnings() {
        return this.e*r;
    }

    public double Rate(){
        return this.r;
    }

    public String toString(){
        return "commission employee: "+super.toString()+"\ngross sales: $"+String.format("%.2f", this.e)
        +"; commission rate: "+String.format("%.2f", this.r);

    }
 

}
