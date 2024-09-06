public class Main {
    public static void main(String[] args) {
        Product p1 = new Product("XA1101","Milk",150);
        Product p2 = new Product("XA1102","Suger",50);
        Product p3 = new Product("XA1103","Coffee",250);
        InventoryCart ic = new InventoryCart (10);
        ic.add(p1);
        ic.add(p2);
        ic.add(p3);
        Cashier c = new Cashier("John Doe");
        c.printReceipt(ic);
       }
}
