import java.util.Scanner;

public class TestLab {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Product[] products = new Product[5];
        products[0] = new Product(0, "Mama", 5.5);//กำหนดค่า
        products[1] = new Product(10, "Lays", 10.5);

        System.out.println("======== Enter Product details ========");
        int[] codes = {20, 30, 40};
        for (int i = 2; i < products.length; i++) {
            System.out.println("Product code " + codes[i - 2]);
            System.out.print("name: ");
            String name = input.nextLine();
            System.out.print("price: ");
            double price = input.nextDouble();
            input.nextLine(); //ขึ้นแถวใหม่
            products[i] = new Product(codes[i - 2], name, price);//สร้างอาร์เรย์ไว้ปริ้น
        }
        System.out.println();
        System.out.println("========== List of Products ==========");
        for (Product product : products) {
            System.out.println(product);//เรียกอาร์เรย์ออกมาปริ้น
        }

        System.out.println();

        System.out.println("===== What do you want to buy? ======");
        double total = 0;

        while (true) {
            System.out.print("Enter product code (press -1 to exit): ");
            int code = input.nextInt();
            if (code == -1) {
                break;
            }

            Product product = null;
            for (Product p : products) {
                if (p.getCode() == code) {
                    product = p;//เงื่อนไขตรงให้เก็บpไว้ในโปรดัก
                    break;
                }
            }

            if (product != null) {
                System.out.print("Amount of " + product.getName() + ": ");
                int amount = input.nextInt();
                total += product.getPrice() * amount;
            } else {
                System.out.println("Invalid product code.");
            }
        }

        System.out.println("You have to pay " + total + " Bath");
    }
}
