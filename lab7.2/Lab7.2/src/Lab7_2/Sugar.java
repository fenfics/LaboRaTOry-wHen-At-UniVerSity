package Lab7_2;

public class Sugar extends Product {
    private double weight;
    private double volumn;

    public Sugar(double price) {
        super(price);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolumn(double volumn) {
       this.volumn=volumn;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getVolumn() {
        return this.volumn;
    }

    public String toString() {
        return "Sugar\t\t" + String.format("%.0f", price);
    }
}
