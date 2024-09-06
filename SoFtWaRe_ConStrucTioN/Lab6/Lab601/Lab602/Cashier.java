public class Cashier {
    private String customer;
    private int total = 0;

    public Cashier(String customer) {
        // รับรถเข็น
        this.customer = customer;
    }

    public void printReceipt(InventoryCart c) {
        // ดึงเอาสินค้าออกจากรถเข็นทั้งหมดเพื่อน ามาแสดงรายการและค านวณสินค้า
        int count = c.getAllProduct().length;// แทนสินค้าทั้งหมดเพื่อมารัน
        System.out.println("\t-------------\t");
        System.out.println("Pumpkin Shop (" + customer + ")");
        for (int i = 0; i < count; i++) {
            int number = 0;
            for (int j = i; j < count; j++) {
                if (c.getProductAt(i).equals(c.getProductAt(j)) && !c.getProductAt(j).isCheck()) {
                    number++;
                    c.getProductAt(j).setCheck(true);
                }
            }
            Product pc = c.getProductAt(i);
            pc.setAmount(number);
            if (number != 0) {
                System.out.println(number + " x " + c.getProductAt(i).toString());
            }
            total += pc.getPrice() * number;
        }
        System.out.println("\t-------------\t");
        System.out.println("\t\tTotal\t\t" + total + "$");
    }
}