// this class represents a dish in the order
public class Dish {
	private RestaurantMenuItem restaurantMenuItem;
	private int quantity;

	public Dish(RestaurantMenuItem restaurantMenuItem, int quantity) {
		this.restaurantMenuItem = restaurantMenuItem;
		this.quantity = quantity;
	}
	public double getPrice() {
		return restaurantMenuItem.getPrice() * quantity;
	}

	@Override
	public String toString() {
		return restaurantMenuItem.getDescription() + ": " + quantity + " (price: " + getPrice() + ")";
	}
}
