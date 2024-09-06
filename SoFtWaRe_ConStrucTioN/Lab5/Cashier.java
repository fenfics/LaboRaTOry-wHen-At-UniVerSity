public class Cashier {
    private String customer;
    private int total=0;
    public Cashier(String customer){
    //รับรถเข็น
    this.customer=customer;
    }

    public void printReceipt(InventoryCart c){
        //ดึงเอาสินค้าออกจากรถเข็นทั้งหมดเพื่อนำมาแสดงรายการและคำนวณสินค้า
        int count = c.getAllProduct().length;//แทนสินค้าทั้งหมดเพื่อมารัน
        System.out.println("Pumpkin Shop ("+customer+")");
        for(int i = 0 ; i<count;i++){
        Product pc = c.getProductAt(i);
        System.out.println("1  x  "+c.getProductAt(i).toString());
        total+=pc.getPrice();
        }
        System.out.println("\t-------------\t");
        System.out.println("\t\tTotal\t\t"+total+"$");
        
    }
}
