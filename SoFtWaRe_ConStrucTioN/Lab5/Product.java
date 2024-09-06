public class Product {
    private String name;
    private int price;
    private String code;
    

    public Product(String code,String name,int price){
        this.code = code;
        this.name = name;
        this.price = price;
    }


    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public String getCode(){
        return this.code;
    }

    public String toString(){
        return name+"\t("+code+")\t"+price;
    }

}
