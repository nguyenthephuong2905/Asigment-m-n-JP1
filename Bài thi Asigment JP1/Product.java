
public class Product {
    private int id;
    private String name;
    private String thumbnail;
    private double price;
    private int qty;
    private String description;

    // Default constructor: initialize with default safe values
    public Product() {
        this.id = 0;
        this.name = "No name";
        this.thumbnail = "";
        this.price = 0.0;
        this.qty = 0;
        this.description = "No description";
    }

    // Optional: parameterized constructor (not required by the spec but convenient)
    public Product(int id, String name, String thumbnail, double price, int qty, String description) {
        // Use setters to ensure validation logic is used
        this();
        setId(id);
        setName(name);
        setThumbnail(thumbnail);
        setPrice(price);
        setQty(qty);
        setDescription(description);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getThumbnail() { return thumbnail; }
    public double getPrice() { return price; }
    public int getQty() { return qty; }
    public String getDescription() { return description; }

    // Setters with validation
    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Warning: Invalid ID (must be positive). ID not changed.");
        }
    }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name.trim();
        } else {
            System.out.println("Warning: Invalid product name (cannot be empty). Name not changed.");
        }
    }

    public void setThumbnail(String thumbnail) {
        // thumbnail can be empty; just assign non-null value
        this.thumbnail = (thumbnail == null) ? "" : thumbnail.trim();
    }

    public void setPrice(double price) {
        if (price >= 0.0) {
            this.price = price;
        } else {
            System.out.println("Warning: Invalid price (must be >= 0). Price not changed.");
        }
    }

    public void setQty(int qty) {
        if (qty >= 0) {
            this.qty = qty;
        } else {
            System.out.println("Warning: Invalid quantity (must be >= 0). Quantity not changed.");
        }
    }

    public void setDescription(String description) {
        this.description = (description == null) ? "" : description.trim();
    }

    // Display product details
    public void displayInfo() {
        System.out.println("\n--- PRODUCT INFORMATION ---");
        System.out.println("ID           : " + id);
        System.out.println("Name         : " + name);
        System.out.printf ("Price        : $%.2f\n", price);
        System.out.println("Quantity     : " + qty);
        System.out.println("Description  : " + description);
        System.out.println("Thumbnail    : " + thumbnail);
    }

    // Check availability for an order quantity
    public boolean checkAvailability(int orderQty) {
        if (orderQty <= 0) {
            System.out.println("Order quantity must be greater than 0.");
            return false;
        }
        if (orderQty > this.qty) {
            System.out.println("Not enough stock. Available: " + this.qty);
            return false;
        }
        return true;
    }

    // Place order: reduce quantity and return total price.
    // If not enough stock or invalid orderQty, no change is made and 0.0 returned.
    public double placeOrder(int orderQty) {
        if (!checkAvailability(orderQty)) {
            System.out.println("Order cannot be placed.");
            return 0.0;
        }
        // Enough stock: reduce qty and compute total
        this.qty -= orderQty;
        double total = orderQty * this.price;
        System.out.printf("Order placed: %d unit(s) of '%s'. Total = $%.2f\n", orderQty, this.name, total);
        System.out.println("Remaining stock: " + this.qty);
        return total;
    }
}
