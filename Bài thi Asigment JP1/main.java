
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product p = new Product();

        System.out.println("=== PRODUCT INVENTORY PROGRAM ===");

        // Read and validate ID
        while (true) {
            System.out.print("Enter Product ID (positive integer): ");
            if (sc.hasNextInt()) {
                int id = sc.nextInt();
                sc.nextLine(); // consume newline
                if (id > 0) {
                    p.setId(id);
                    break;
                } else {
                    System.out.println("ID must be positive. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine(); // discard invalid token
            }
        }

        // Read and validate Name
        while (true) {
            System.out.print("Enter Product Name (non-empty): ");
            String name = sc.nextLine();
            if (name != null && !name.trim().isEmpty()) {
                p.setName(name);
                break;
            } else {
                System.out.println("Name cannot be empty. Try again.");
            }
        }

        // Thumbnail path (optional)
        System.out.print("Enter Thumbnail Path (can be empty): ");
        String thumb = sc.nextLine();
        p.setThumbnail(thumb);

        // Read and validate Price
        while (true) {
            System.out.print("Enter Price (>= 0): ");
            if (sc.hasNextDouble()) {
                double price = sc.nextDouble();
                sc.nextLine();
                if (price >= 0.0) {
                    p.setPrice(price);
                    break;
                } else {
                    System.out.println("Price must be >= 0. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }

        // Read and validate Quantity
        while (true) {
            System.out.print("Enter Quantity in stock (integer >= 0): ");
            if (sc.hasNextInt()) {
                int qty = sc.nextInt();
                sc.nextLine();
                if (qty >= 0) {
                    p.setQty(qty);
                    break;
                } else {
                    System.out.println("Quantity must be >= 0. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }

        // Read description
        System.out.print("Enter Description (can be empty): ");
        String desc = sc.nextLine();
        p.setDescription(desc);

        // Display product info
        p.displayInfo();

        // Order flow
        System.out.println("\n--- ORDER PROCESS ---");
        int orderQty = 0;
        while (true) {
            System.out.print("Enter quantity to order (integer > 0): ");
            if (sc.hasNextInt()) {
                orderQty = sc.nextInt();
                sc.nextLine();
                if (orderQty > 0) {
                    break;
                } else {
                    System.out.println("Order quantity must be > 0. Try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.nextLine();
            }
        }

        // Check availability and place order
        if (p.checkAvailability(orderQty)) {
            double total = p.placeOrder(orderQty);
            System.out.printf("Total price charged: $%.2f\n", total);
        } else {
            System.out.println("Not enough stock or invalid order quantity. Order was not placed.");
        }

        System.out.println("\nFinal product state:");
        p.displayInfo();

        sc.close();
    }
}
