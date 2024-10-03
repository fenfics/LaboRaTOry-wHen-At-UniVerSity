import java.io.*;
import java.util.*;

public class ItemDAO {
    private static final String FILE_NAME = "sell.txt";

    public void saveItems(List<Item> items) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Item item : items) {
                writer.println(item.getName() + "," + item.getPrice() + "," + item.getQuantity());
            }
        } catch (IOException e) {
            System.out.println("Error saving items: " + e.getMessage());
        }
    }

    public List<Item> loadItems() {
        List<Item> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    double price = Double.parseDouble(parts[1]);
                    int quantity = Integer.parseInt(parts[2]);
                    items.add(new Item(name, price, quantity));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
        return items;
    }
}