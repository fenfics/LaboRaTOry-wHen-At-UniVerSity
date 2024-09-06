package Lab7_2;

public class Cashier {
    private double total;
    private InventoryCart cart;
    private DebitCard card;

    public void printReceipt() {
        total = 0;

        System.out.println("\t-------------\t");
        System.out.println("\tPumpkin Shop\t");
        System.out.println();
        Product[] products = cart.getAllProduct();
        for (Product product : products) {
            if (product == null) break;
            if (!product.isCheck()) {
                int count = countProduct(products, product);
                System.out.printf("      %d x %s\n", count, product.toString());
                total += product.getPrice() * count;
            }
        }
        System.out.println("\t-------------\t");
        System.out.printf("\tCARD DISCOUNT %.1f%%%n", this.card.discount());
        double discountedTotal = total * (1 - this.card.discount() / 100);
        System.out.printf("\tTotal\t      %.1f%n", discountedTotal);
    }

    private int countProduct(Product[] products, Product product) {
        int count = 0;
        for (Product p : products) {
            if (p == null) break;
            if (p.equals(product) && !p.isCheck()) {
                count++;
                p.setCheck(true);
            }
        }
        return count;
    }

    public void doPayment(InventoryCart cart, DebitCard card) {
        this.cart = cart;
        this.card = card;
        System.out.println("\tCARD TYPE: " + card.type());
        System.out.println("  CARD NUMBER: " + maskCardNumber(card.getCardNumber()));
    }

    private String maskCardNumber(String cardNumber) {
        return "xxx-xxx-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
