package lab8;

public class Coffee extends Product {
    private double weight;
    private double valumn;

    public Coffee(double price) {
        super(price);
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setVolumn(double volumn) {
        this.valumn=volumn;
    }

    public double getWeight() {
        return this.weight;
    }

    public double getValumn(){
        return this.valumn;
    }

    public String toString() {
        return "Coffee\t" + price;
    }
}
