public class Product{
    private int Code;
    private double Price;
    private String Name;
    
    
    public Product(int code, String name, double price) {
        this.Code = code;
        this.Name = name;
        this.Price = price;
    }

    public String getName(){
        return this.Name;
    }

    public int getCode(){
        return this.Code;
    }

    public double getPrice(){
        return this.Price;
    }

    public String toString() {
        return "Product code " + Code + "\nName: " + Name + " , Price: " + Price;
    }
}
