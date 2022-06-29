import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

// this class represents an order
public class Order {
	// the dishes in the order
	private ArrayList<Dish> dishes = new ArrayList<Dish>();

	public Order() {
	}
	// add a dish to the order
	public void add(Dish dish) {
		dishes.add(dish);
	}
	@Override
	public String toString() {
		String orderString = "";
		double price = 0;
		for (Dish dish: dishes) {
			orderString += dish.toString() + '\n';
			price += dish.getPrice();
		}
		orderString += "Total price: " + price;
		return orderString;
	}
	// save the order to a file
	public void save(String fileName) {
		try {
			PrintWriter out = new PrintWriter(fileName);
			out.print(toString());
			out.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
