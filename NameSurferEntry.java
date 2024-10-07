
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	private String line;
	private String name;
	private int[] ranks = new int[11];

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers
	 * giving the rank of that name for each decade.
	 */
	public NameSurferEntry(String line) {
		this.line = line;
		String[] splitLine = line.split(" ");
		setPropertiesValues(splitLine);

	}

	// This function helps us to set values to name and ranks properties
	private void setPropertiesValues(String[] splitLine) {
		name = splitLine[0];
		for (int i = 1; i < splitLine.length; i++) {
			ranks[i - 1] = Integer.parseInt(splitLine[i]);
		}
	}

	/* Method: getName() */
	/**
	 * Returns the name associated with this entry.
	 */
	public String getName() {
		return name;
	}

	/* Method: getRank(decade) */
	/**
	 * Returns the rank associated with an entry for a particular decade. The
	 * decade value is an integer indicating how many decades have passed since
	 * the first year in the database, which is given by the constant
	 * START_DECADE. If a name does not appear in a decade, the rank value is 0.
	 */
	public int getRank(int decade) {

		return ranks[decade - 1];
	}

	/* Method: toString() */
	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	public String toString() {
		String result = name + " [";
		for (Integer rank : ranks) {
			result += (rank.toString() + " ");
		}
		result += ']';

		return result;
	}
}
