package lab8;

public abstract class Product {
    protected double price;
    protected boolean checked;

    public abstract void setWeight(double weight);

    public abstract void setVolumn(double volumn);

    public Product(double price) {
        this.price = price;
        this.checked = false;
    }

    public boolean equals(Object otherObj) {
        if (this == otherObj)
            return true;
        if (otherObj == null || !(otherObj instanceof Product))
            return false;

        Product other = (Product) otherObj;
        return this.getClass().getSimpleName().equals(other.getClass().getSimpleName());
    }

    
    public double getPrice() {
        return  price;
    }

    public void setCheck(boolean checked) {
        this.checked = checked;
    }

    public boolean isCheck() {
        return checked;
    }
}
