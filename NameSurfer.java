
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	private final JLabel label = new JLabel("NAME");
	private JTextField textField;
	private JButton graphButton;
	private JButton clearButton;
	private NameSurferGraph graph;
	private NameSurferDataBase database;

	/* Method: init() */
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */
	public void init() {

		graph = new NameSurferGraph();
		add(graph);
		graph.update();
		database = new NameSurferDataBase(NAMES_DATA_FILE);

		add(label, SOUTH);

		textField = new JTextField(20);
		add(textField, SOUTH);
		textField.addActionListener(this);

		graphButton = new JButton("Graph");
		add(graphButton, SOUTH);

		clearButton = new JButton("Clear");
		add(clearButton, SOUTH);

		addActionListeners();
	}

	public NameSurfer() {

	}

	/* Method: actionPerformed(e) */
	/**
	 * This class is responsible for detecting when the buttons are clicked, so
	 * you will have to define a method to respond to button actions.
	 */
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == graphButton || e.getSource() == textField) {
			String name = textField.getText();
			NameSurferEntry entry = database.findEntry(name);
			if (isDrawn(entry)) {
				graph.addEntry(entry);
			}
			graph.update();
			textField.setText("");
		} else if (e.getSource() == clearButton) {
			graph.clear();
		}

	}

	// This method checks if graphic is already drawn for that name
	private boolean isDrawn(NameSurferEntry namesurferEntry) {
		for (NameSurferEntry entry : graph.listOfentries) {
			if (entry.getName().equals(namesurferEntry.getName())) {
				return false;
			}
		}
		return true;
	}
}
