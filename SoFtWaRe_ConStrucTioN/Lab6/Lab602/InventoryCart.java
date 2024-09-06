public class InventoryCart {
    private static int numproduct = 0;
    private Product[] products;
    public InventoryCart(int num){
        products = new Product[num];//กำหนดจำนวนสินค้า
    }

    public void add(Product p){
    //นำสินค้าใส่รถ
        products[numproduct++]= p;
        
    }

    public Product getProductAt(int index){
    //นำสินค้าออกจากรถตามที่เลือก
    return products[index];

    }

    public Product[] getAllProduct(){
    //นำสินค้าทั้งหมดออกจากรถ
    Product[] pd = new Product[numproduct];
    for(int i =0;i<numproduct;i++)
    pd[i]=products[i];
    return pd;
    }

    
}
