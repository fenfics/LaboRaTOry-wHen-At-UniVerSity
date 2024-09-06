package Lab7_2;

public class InventoryCart {
    private Product[] products;
    private int currentIndex;

    public InventoryCart(int capacity) {
        products = new Product[capacity];
        currentIndex = 0;
    }

    public void add(Product product) {
        if (currentIndex < products.length) {
            products[currentIndex++] = product;
        }
    }

    public Product[] getAllProduct() {
        return products;
    }

    public Product getProductAt(int index) {
        if (index >= 0 && index < currentIndex) {
            return products[index];
        }
        return null;
    }
}
