import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// this class represents a restaurant menu
public class RestaurantMenu {
	// the menu items
	private ArrayList<RestaurantMenuItem> menuItems = new ArrayList<RestaurantMenuItem>();

	public RestaurantMenu() {
	}
	// read the menu from the "menu.txt" file
	public void read() throws Exception {
		Scanner input = new Scanner(new File("menu.txt"));
		while (input.hasNext()){
			String description = input.next();
			if (!input.hasNext())
				break;
			String kind = input.next();
			if (!input.hasNext())
				break;
			double price = input.nextDouble();
			RestaurantMenuItem restaurantMenuItem = new RestaurantMenuItem(description, kind, price);
			menuItems.add(restaurantMenuItem);
		}
		input.close();
	}
	// get the items of a specific kind
	public ArrayList<RestaurantMenuItem> getItems(String kind) {
		ArrayList<RestaurantMenuItem> arr = new ArrayList<RestaurantMenuItem>();
		for (RestaurantMenuItem menuItem : menuItems) {
			if (menuItem.getKind().equals(kind))
				arr.add(menuItem);
		}
		return arr;
	}
	public int getSize() {
		return menuItems.size();
	}
}
