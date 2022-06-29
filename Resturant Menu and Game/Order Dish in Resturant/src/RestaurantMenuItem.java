
// this class represents an item in the menu of the restaurant
public class RestaurantMenuItem {
	// the description
	private String description;
	// the kind like: Drink
	private String kind;
	// the price
	private double price;

	public RestaurantMenuItem(String description, String kind, double price) {
		this.description = description;
		this.kind = kind;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public String getKind() {
		return kind;
	}

	public double getPrice() {
		return price;
	}

}
