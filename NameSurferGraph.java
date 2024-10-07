
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import acm.program.GraphicsProgram;

import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {

	public ArrayList<NameSurferEntry> listOfentries;

	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */
	public NameSurferGraph() {
		listOfentries = new ArrayList<NameSurferEntry>();
		addComponentListener(this);
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */
	public void clear() {
		removeAll();
		listOfentries.clear();
		drawBackGround();
		drawDecadesLabels();
	}

	/* Method: addEntry(entry) */
	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note
	 * that this method does not actually draw the graph, but simply stores the
	 * entry; the graph is drawn by calling update.
	 */
	public void addEntry(NameSurferEntry entry) {
		if (entry != null) {
			listOfentries.add(entry);
		}
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of
	 * entries. Your application must call update after calling either clear or
	 * addEntry; update is also called whenever the size of the canvas changes.
	 */
	public void update() {
		removeAll();
		drawBackGround();
		drawDecadesLabels();
		drawGraphs(listOfentries);
	}

	// This function draws labels of decades at the botton of program
	private void drawDecadesLabels() {
		int decade = 1900;
		double columnWidth = getWidth() / 11;
		double y = getHeight() - BOTTOM_DISTANCE;
		for (int i = 1; i <= 11; i++) {
			GLabel label = new GLabel("" + decade);
			double x = (i - 1) * columnWidth + 5;
			add(label, x, y);
			decade += 10;
		}
	}

	// This function draws graphs of all entries
	private void drawGraphs(ArrayList<NameSurferEntry> listOfEntries) {
		for (int i = 0; i < listOfEntries.size(); i++) {
			drawEachLineGraph(listOfEntries.get(i), i);
		}
	}

	// This function draws graph of each entry
	private void drawEachLineGraph(NameSurferEntry entry, int j) {
		GPoint startPoint = countPoints(entry, 1);
		drawLabel(startPoint, entry, 1, j + 1);
		for (int i = 1; i < NDECADES; i++) {

			GPoint endPoint = countPoints(entry, i + 1);
			GLine line = new GLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
			line.setColor(getColor(j + 1));
			add(line);
			drawLabel(endPoint, entry, i + 1, j + 1);
			startPoint = endPoint;

		}
	}

	// This function returns Color for appropiate graph
	private Color getColor(int i) {
		if (i % 4 == 1) {
			return Color.black;
		} else if (i % 4 == 2) {
			return Color.red;
		} else if (i % 4 == 3) {
			return Color.blue;
		} else {
			return Color.yellow;
		}
	}

	// THis function helps us to find Y and X of the point of data
	private GPoint countPoints(NameSurferEntry entry, int i) {
		double x;
		double y;
		double oneRank = (getHeight() - BOTTOM_DISTANCE - (BOTTOM_DISTANCE + 20)) * 1.0 / 1000;

		int columnWidth = getWidth() / 11;
		if (entry.getRank(i) == 0) {
			y = getHeight() - BOTTOM_DISTANCE - 20;
		} else {
			y = entry.getRank(i) * oneRank + BOTTOM_DISTANCE;
		}
		x = (i - 1) * columnWidth;

		return new GPoint(x, y);
	}

	// This func draws labels of each function for each point of graph
	private void drawLabel(GPoint point, NameSurferEntry entry, int i, int j) {
		String name = entry.getName();
		if (entry.getRank(i) == 0) {
			name += " *";
		} else {
			name += (" " + entry.getRank(i));
		}
		GLabel label = new GLabel(name);
		label.setColor(getColor(j));
		add(label, point.getX() + 3, point.getY() - 5);
	}

	// This func draws background of the program, vertical and horizontal lines
	private void drawBackGround() {
		int nLine = NDECADES;
		double x = 0;
		double y = 0;
		for (int i = 0; i < nLine; i++) {
			GLine line = new GLine(x, y, x, getHeight());
			add(line);
			x += getWidth() / nLine;
		}

		GLine line1 = new GLine(0, BOTTOM_DISTANCE, getWidth(), BOTTOM_DISTANCE);
		add(line1);

		GLine line2 = new GLine(0, getHeight() - BOTTOM_DISTANCE - 20, getWidth(), getHeight() - BOTTOM_DISTANCE - 20);
		add(line2);
	}

	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}
}
