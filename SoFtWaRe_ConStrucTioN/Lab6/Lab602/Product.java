public class Product {
    private int amount;
    protected String name;
    protected int price;
    protected String code;
    protected int weight;
    private boolean check;
    

    public Product(String code,String name,int price){
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public boolean equals(Object otherObj){
        if(this==otherObj)return true;
        if(otherObj == null || !(otherObj instanceof Product))
        return false;

        Product other = (Product) otherObj;
        return (this.getClass().getSimpleName()==other.getClass().getSimpleName());
    }

     public Product(int p) {
        this.price=p;
     }

    public void setWeight(int weight){
        this.weight=weight;
    }

    public int getWeight(){
        return this.weight;
    }

    public boolean setCheck(boolean check){
        return this.check=check;
    }

    public boolean isCheck(){
        return this.check;
    }

    public void setAmount(int amount){
        this.amount=amount;
    }

    public int getAmount(){
        return this.amount;
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
        return getClass().getSimpleName()+"\t"+this.getWeight()+"\tGram\t"+(this.getPrice()*amount);
    }

}
