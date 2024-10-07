import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import acm.program.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	private ArrayList<String> dataBase = new ArrayList<String>();
	public ArrayList<ArrayList<String>> splitedDatabase = new ArrayList<ArrayList<String>>();

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the
	 * requested file does not exist or if an error occurs as the file is being
	 * read.
	 */
	public NameSurferDataBase(String filename) {
		dataBase = readData(filename);
		splitData(dataBase);
	}

	// This func reads file and write info in arrayList
	private ArrayList<String> readData(String filename) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
				data.add(line);

			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return data;
	}

	// This func splits Strings of the arrayList and write them in to 2D array
	// List
	private void splitData(ArrayList<String> data) {
		for (String line : data) {
			ArrayList<String> lineToList = new ArrayList<>(Arrays.asList(line.split(" ")));
			splitedDatabase.add(lineToList);
		}

	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If
	 * the name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {

		for (int i = 0; i < splitedDatabase.size(); i++) {
			if (splitedDatabase.get(i).get(0).equals(name)) {

				String line = dataBase.get(i);
				return new NameSurferEntry(line);
			}
		}

		return null;
	}
}
