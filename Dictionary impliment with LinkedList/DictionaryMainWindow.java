import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

// class for the main window
public class DictionaryMainWindow extends JFrame {
	// the window width
	static private final int WINDOW_WIDTH = 1000;
	// the window height
	static private final int WINDOW_HEIGHT = 500;
	// the dictionary
	private Dictionary dictionary = new Dictionary();
	// the table of the dictionary
	private JTable dictionaryTable = new JTable();
	public DictionaryMainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(new BorderLayout());
		// panel for the buttons
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new GridLayout(0, 1));
		// create the all buttons
		JButton loadButton = new JButton("Load");
		leftPanel.add(loadButton);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				load();
			}
		});
		JButton saveButton = new JButton("Save");
		leftPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});

		JButton addButton = new JButton("Add");
		leftPanel.add(addButton);
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});

		JButton removeButton = new JButton("Remove");
		leftPanel.add(removeButton);
		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove();
			}
		});

		JButton updateButton = new JButton("Update");
		leftPanel.add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});

		// panel for the table
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout());
		JScrollPane scrollableTable = new JScrollPane(dictionaryTable);
		centerPanel.add(scrollableTable, BorderLayout.CENTER);
		// set the model for the table
		dictionaryTable.setModel(new TableModel() {
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			}
			
			@Override
			public void removeTableModelListener(TableModelListener l) {
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
			}
			
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				return dictionary.getValueAt(rowIndex, columnIndex);
			}
			
			@Override
			public int getRowCount() {
				return dictionary.getSize();
			}
			
			@Override
			public String getColumnName(int columnIndex) {
				if (columnIndex == 0)
					return "Term";
				return "Description";
			}
			
			@Override
			public int getColumnCount() {
				return 2;
			}
			
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
			
			@Override
			public void addTableModelListener(TableModelListener l) {
			}
		});
	}
	// update the table according to the dictionary
	private void refreshTable() {
		dictionaryTable.revalidate();
	}
	// add a new term
	private void add() {
		// fields for the term and the description
		JTextField termField = new JTextField();
		JTextField descriptionField = new JTextField();
		Object[] inputFields = {"Term", termField,
                "Description", descriptionField};
        int option = JOptionPane.showConfirmDialog(this, inputFields, "Multiple Inputs", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
        if (option == JOptionPane.OK_OPTION) {
        	dictionary.add(termField.getText(), descriptionField.getText());
			dictionaryTable.clearSelection();
			refreshTable();
        }
	}
	// update the description of an existing term
	private void update() {
		int selectedRow = dictionaryTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null,
					"No line was selected", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// get the term of the selected row
		String term = (String) dictionaryTable.getModel().getValueAt(selectedRow, 0);
		String description = (String) dictionary.getValueAt(selectedRow, 1);
		JTextField descriptionField = new JTextField(description);
	    int ret = JOptionPane.showConfirmDialog(this, descriptionField, "Change value", JOptionPane.OK_OPTION);
	    if (ret == 0) {
	    	dictionary.update(term, descriptionField.getText());
	    	// unselect the selected row
			dictionaryTable.clearSelection();
			refreshTable();
	    }
	}
	// remove a term
	private void remove() {
		int selectedRow = dictionaryTable.getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(null,
					"No line was selected", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		// get the key of the selected row
		String key = (String) dictionaryTable.getModel().getValueAt(selectedRow, 0);
		dictionary.remove(key);
    	// unselect the selected row
		dictionaryTable.clearSelection();
		refreshTable();
	}
	// load a dictionary from a file
	private void load() {
		// choose the file
		FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
		fd.setFile("*.txt");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null) {
			JOptionPane.showMessageDialog(null,
					"No file was selected", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				dictionary.load(filename);
				refreshTable();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// save the dictionary to a file
	private void save() {
		// choose the file
		FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.SAVE);
		fd.setFile("*.txt");
		fd.setVisible(true);
		String filename = fd.getFile();
		if (filename == null) {
			JOptionPane.showMessageDialog(null,
					"No file was selected", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		else {
			try {
				dictionary.save(filename);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						e.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static void main(String[] args) {
		DictionaryMainWindow window = new DictionaryMainWindow();
		window.setVisible(true);
	}
}
