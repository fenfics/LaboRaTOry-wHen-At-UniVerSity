package Lab7_2;

public class Milk extends Product {
    private double volumn;
    private double weight;

    public Milk(double price) {
        super(price);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolumn(double volumn) {
        this.volumn = volumn;
    }

    public double getValumn() {
        return this.volumn;
    }

    public double getWeight() {
        return this.weight;
    }

    public String toString() {
        return "Milk\t\t" + String.format("%.0f", price);
    }
}