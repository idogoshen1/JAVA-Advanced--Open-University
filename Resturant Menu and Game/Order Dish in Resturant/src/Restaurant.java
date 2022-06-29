import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Restaurant extends JFrame {
	// the window width
	static final int WINDOW_WIDTH = 1000;
	// the window height
	static final int WINDOW_HEIGHT = 500;
	// the kinds of items
	private String menuKinds[] = {"Main", "First", "Last", "Drink"};
	private RestaurantMenu restaurantMenu = new RestaurantMenu();
	// all items
	private ArrayList<RestaurantMenuItem> items = new ArrayList<RestaurantMenuItem>();
	// the combo boxes
	private ArrayList<JComboBox<Integer>> combos = new ArrayList<JComboBox<Integer>>();
	// the check boxex
	private ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();

	// constructor
	public Restaurant() {
		try {
			restaurantMenu.read();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		setLayout(new BorderLayout());
		// the panel of the menu
		JPanel mainPanel = new JPanel();
		// add scroll for long menues
		JScrollPane pane = new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(pane, BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(new GridLayout(menuKinds.length + restaurantMenu.getSize(), 1));
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		// add for each kind...
		for (int i = 0; i < menuKinds.length; i++) {
			ArrayList<RestaurantMenuItem> itemOfKind = restaurantMenu.getItems(menuKinds[i]);
			mainPanel.add(new JLabel(menuKinds[i] + ":"));
			// add the menu items after each kind
			for (RestaurantMenuItem item : itemOfKind) {
				items.add(item);
				JPanel panel = new JPanel();
				panel.setLayout(new GridLayout(1, 4));
				JLabel label = new JLabel(item.getDescription());
				panel.add(label);
				JCheckBox checkBox = new JCheckBox("Order");
				checkBoxes.add(checkBox);
				panel.add(checkBox);
				panel.add(new JLabel(String.valueOf(item.getPrice())));
				JComboBox<Integer> combo = new JComboBox<>();
				combos.add(combo);
				for (int j = 0; j < 10; j++) {
					combo.addItem(j + 1);
				}
				panel.add(combo);
				mainPanel.add(panel);
			}
		}
		// add the "Order" button
		JButton makeOrder = new JButton("Order");
		add(makeOrder, BorderLayout.SOUTH);
		makeOrder.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Order order = new Order();
				// prepare the order
				for (int i = 0; i < checkBoxes.size(); i++) {
					JCheckBox checkBox = checkBoxes.get(i);
					if (!checkBox.isSelected())
						continue;
					int quantity = (Integer)combos.get(i).getSelectedItem();
					RestaurantMenuItem restaurantMenuItem = items.get(i);
					Dish dish = new Dish(restaurantMenuItem, quantity);
					order.add(dish);
				}
				String[] options = {"Order", "Update", "Cancel"};
				// ask the user what to do
				int x = JOptionPane.showOptionDialog(null, order.toString(),
		                "Click a button",
		                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
				switch(x) {
				case 0:
					// get the name and ID of the user and save to the file
					String nameAndID = JOptionPane.showInputDialog("Enter your name followed by your ID");
					order.save(nameAndID);
					reset();
					break;
				case 1:
					// back to update the order
					return;
				case 2:
					// cancel the order
					reset();
					break;
				}
			}
		});
	}
	// clean the order
	private void reset() {
		for (JComboBox<Integer> combo : combos) {
			combo.setSelectedIndex(0);
		}
		for (JCheckBox checkBox : checkBoxes) {
			checkBox.setSelected(false);
		}
	}
	public static void main(String[] args) {
		Restaurant restaurant = new Restaurant();
		restaurant.setVisible(true);
	}
}
