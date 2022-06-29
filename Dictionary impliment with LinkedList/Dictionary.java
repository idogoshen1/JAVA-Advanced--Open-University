import java.io.File;
import java.util.Formatter;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

// a class for the dictionary
public class Dictionary {
	// the elements of the dictionary
	SortedMap<String, String> elements = new TreeMap<String, String>();
	// the keys of the dictionary
	Object terms[];

	// load a dictionary from a file
	public void load(String filename) throws Exception {
		Scanner input = new Scanner(new File(filename));
		// read line by line
		while (input.hasNext()){
			String term = input.nextLine();
			if (!input.hasNext())
				break;
			String description = input.nextLine();
			add(term, description);
		}
		refresh();
	}

	// refresh the terms array
	private void refresh() {
		terms = elements.keySet().toArray();
	}
	// add a new term
	public void add(String term, String description) {
		elements.put(term, description);
		refresh();
	}
	// remove a term
	public void remove(String term) {
		elements.remove(term);
		refresh();
	}
	// save the dictionary to a file
	public void save(String filename) throws Exception {
		Formatter output = new Formatter(filename);
		Set<String> tempTerms = elements.keySet();
		for (String term : tempTerms) {
			output.format("%s\n",term);
			output.format("%s\n",elements.get(term));
		}
		output.close();
	}
	// update the description of a term
	public void update(String term, String description) {
		elements.put(term, description);
		refresh();
	}
	// return the number of elements
	public int getSize() {
		return elements.size();
	}
	// return the value for the table
	public Object getValueAt(int rowIndex, int columnIndex) {
		String entry = (String) terms[rowIndex];
		if (columnIndex == 0)
			return entry;
		return elements.get(entry);
	}
}
