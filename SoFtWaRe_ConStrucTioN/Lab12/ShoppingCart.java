package Lab1201;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        ArrayList<Item> cart = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the name of the item: ");
            String name = scanner.nextLine();

            double price = 0;
            int quantity = 0;

            // Input validation for price
            while (true) {
                try {
                    System.out.print("Enter the unit price: ");
                    price = scanner.nextDouble();
                    if (price < 0) {
                        System.out.println("Price must be non-negative. Please try again.");
                        continue;
                    }
                    break; // Exit loop if valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }

            // Input validation for quantity
            while (true) {
                try {
                    System.out.print("Enter the quantity: ");
                    quantity = scanner.nextInt();
                    if (quantity < 0) {
                        System.out.println("Quantity must be non-negative. Please try again.");
                        continue;
                    }
                    break; // Exit loop if valid
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    scanner.nextLine(); // Clear the invalid input
                }
            }
            scanner.nextLine(); // Consume newline

            cart.add(new Item(name, price, quantity));

            System.out.print("Continue shopping (y/n)? ");
            String continueShop = scanner.nextLine().toLowerCase();
            if (!continueShop.equals("y")) {
                break;
            }
        }

        System.out.println("\nFinal Shopping Cart totals");
        double total = 0;
        System.out.printf("%-15s %-10s %-10s %-10s%n", "Item Name", "Unit Price", "Quantity", "Total Price");
        for (Item item : cart) {
            System.out.printf("%-15s $%.2f %10d %10.2f%n", 
                              item.getName(), item.getPrice(), item.getQuantity(), item.getTotalPrice());
            total += item.getTotalPrice();
        }
        System.out.printf("Total $ Amount in Cart: $%.2f%n", total);

        scanner.close();
    }
}
