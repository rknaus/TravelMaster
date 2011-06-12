package ch.netgeek.travelmaster.gui;

import ch.netgeek.travelmaster.algorithm.RouteCalculator;
import ch.netgeek.travelmaster.algorithm.Stopover;
import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.DepartureItem;
import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TransportNetwork;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * This class represents the TravelMaster GUI.<br>
 * It is divided into three areas:<br>
 * - The input area where the user can enter the timetable requests<br>
 * - The output area where the timetable gets displayed<br>
 * - The map of the transport network as an overview for the user
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     1.0
 *
 */
public class GUI {

	// general variable declaration
	private TransportNetwork transportNetwork;
	private RouteCalculator routeCalculator;
	private JTable stopoverTable;
	private TableView tableView;
	private ArrayList<Stopover> stopoverList;

	// GUI variable declaration
	private JFrame frame;
	private JPanel bannerPanel;
	private JPanel ioPanel;
	private MapPanel mapPanel;
	private JComboBox fromComboBox;
	private JComboBox toComboBox;
	private JButton showButton;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JTextField timeTextField;
	private String fromTextFieldValue;
	private String toTextFieldValue;
	private String timeTextFieldValue;
	private JButton clearButton;
	private JButton searchButton;

	/**
	 * Initializes the GUI.
	 * 
	 * @param transportNetwork      The transport network
	 * @param routeCalculator       The route calculator
	 */
	public GUI(TransportNetwork transportNetwork, RouteCalculator routeCalculator, 
			TableController tableController) {
		this.transportNetwork = transportNetwork;
		this.routeCalculator = routeCalculator;
		tableView = tableController.getView();
		stopoverTable = tableView.getTable();
		stopoverTable.setVisible(true);
		stopoverTable.setRowSelectionInterval(0, 0);
		stopoverList = tableController.getStopoverList();

		// creates the frame
		createFrame();
	}

	/**
	 * Creates the GUI layout. 
	 */
	public void createFrame() {

		frame = new JFrame("TravelMaster");

		// creates the menu bar
		createMenuBar();

		// creates the banner panel
		createBannerPanel();

		// creates the input/output panel
		createIOPanel();

		// creates the transport network map
		createMapPanel();

		// set parameters like size, close operation, visibility, etc.
		frame.setSize(1200, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Creates the menu bar of the GUI and adds it to the frame.
	 */
	private void createMenuBar() {

		// generates a menu bar (JMenuBar) and adds it to the window (JFrame)
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		// generates a menu (JMenu) and adds it to the menu bar (JMenuBar)
		JMenu fileMenu = new JMenu("File");
		fileMenu.setFont(new Font("arial", 0, 12));
		menuBar.add(fileMenu);

		// generates menu items (JMenuItem) and adds them to the menu (JMenu)
		JMenuItem aboutItem = new JMenuItem("About TravelMaster");
		aboutItem.addActionListener(new AboutActionListener());
		aboutItem.setFont(new Font("arial", 0, 12));
		fileMenu.add(aboutItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ExitActionListener());
		exitItem.setFont(new Font("arial", 0, 12));
		fileMenu.add(exitItem);
	}

	/**
	 * Action listener for the "about" menu entry.<br>
	 * It displays a popup window with some information about the software.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class AboutActionListener implements ActionListener {

		/**
		 * Performed action when pressing the "about" menu item.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"<html><body><p>TravelMaster</p><br><p>" +
					"Version 1.0.0" + "<br>Software Project 2, 2011</p><br>" +
					"<p>Founders:<br>Ruben Knaus & Dieu Van</p><p>Hochschule " +
					"für Technik Zurich (HSZ-T), i09c</p><br><p>(c) Copyright" +
					" TravelMaster contributors and founders 2011. All rights" +
					" reserved.</p><br></body></html>", "About TravelMaster", 
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Action listener for the "exit" menu entry.<br>
	 * It closes the program.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class ExitActionListener implements ActionListener {

		/**
		 * Performed action when pressing the "exit" menu item.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	/**
	 * Creates a banner panel on the top position of the GUI.<br>
	 * It displays the name/logo of the software.
	 */
	private void createBannerPanel() {

		// creates the banner panel and sets its layout
		bannerPanel = new JPanel();
		FlowLayout bannerLayout = new FlowLayout(FlowLayout.LEFT);
		bannerLayout.setHgap(0);
		bannerPanel.setLayout(bannerLayout);
		bannerPanel.setSize(1200, 50);

		// adds a label to the banner panel
		JLabel bannerLabel = new JLabel(" TRAVELMASTER");
		bannerLabel.setFont(new Font("arial", 1, 30));
		bannerLabel.setForeground(Color.GRAY);
		bannerPanel.add(bannerLabel);

		// adds the panel to the frame
		frame.getContentPane().add(BorderLayout.NORTH, bannerPanel);
	}

	/**
	 * Creates an input/output panel on the left position of the GUI.<br>
	 * It displays the input mask for the timetable calculation (upper half) 
	 * and the result of the timetable calculation (lower half)
	 */
	private void createIOPanel() {

		// creates the input/output panel and sets its layout
		ioPanel = new JPanel();
		ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.Y_AXIS));
		ioPanel.setPreferredSize(new Dimension(440, 700));
		ioPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
		ioPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);

		// creates the departures panel and adds it to the input/output panel
		createDeparturesPanel();

		// creates the input panel and adds it to the input/output panel
		createInputPanel();

		// creates the input panel and adds it to the input/output panel
		createOutputPanel();

		// adds the IO Panel to the frame
		frame.getContentPane().add(BorderLayout.WEST, ioPanel);
	}

	/**
	 * Creates a departure panel at the top of the input/output panel.<br>
	 * It gives the user the possibility to display a departure list for a 
	 * source and a destination station.
	 */
	private void createDeparturesPanel() {

		// definition for the text- and layout format
		FlowLayout ioLabelLayout = new FlowLayout(FlowLayout.LEFT);
		ioLabelLayout.setVgap(0);
		Font font = new Font("arial", 0, 14);

		// creates the departure panel and sets its layout
		JPanel departuresPanel = new JPanel();
		departuresPanel.setLayout(new BoxLayout(departuresPanel, BoxLayout.Y_AXIS));

		// Sets the size and alignement of the departures panel
		departuresPanel.setPreferredSize(new Dimension(440, 120));
		departuresPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		// adds a title label to the departure panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(ioLabelLayout);
		JLabel titleLabel = new JLabel();
		titleLabel.setPreferredSize(new Dimension(375, 30));
		titleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		titleLabel.setFont(new Font("arial", 1, 18));
		titleLabel.setText(" Departures");
		titlePanel.add(titleLabel);
		departuresPanel.add(titlePanel);

		// adds a departure combo box to the departure panel
		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(ioLabelLayout);
		JLabel fromLabel = new JLabel(" From:");
		fromLabel.setPreferredSize(new Dimension(50, 10));
		fromLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		fromLabel.setFont(font);
		fromPanel.add(fromLabel);
		fromComboBox = new JComboBox();
		fromComboBox.setEditable(false);
		fromComboBox.setPreferredSize(new Dimension(350, 30));
		fromComboBox.addActionListener(new fromComboBoxListener());
		ArrayList<Station> stationList = transportNetwork.getStationList();
		for (Station station : stationList) {
			fromComboBox.addItem(station.getName());
		}
		fromPanel.add(fromComboBox);
		departuresPanel.add(fromPanel);

		// adds an arrival combo box to the departure panel
		JPanel toPanel = new JPanel();
		toPanel.setLayout(ioLabelLayout);
		JLabel toLabel = new JLabel(" To:");
		toLabel.setPreferredSize(new Dimension(50, 10));
		toLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		toLabel.setFont(font);
		toPanel.add(toLabel);
		toComboBox = new JComboBox();
		toComboBox.setEditable(false);
		toComboBox.setPreferredSize(new Dimension(350, 30));
		setToComboBoxItems();
		toPanel.add(toComboBox);
		departuresPanel.add(toPanel);

		// adds a show button to the departure panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(ioLabelLayout);
		showButton = new JButton("Show");
		showButton.setPreferredSize(new Dimension(90, 25));
		showButton.setBackground(Color.LIGHT_GRAY);
		showButton.addActionListener(new ShowActionListener());
		buttonPanel.add(showButton);
		departuresPanel.add(buttonPanel);

		// adds the departure panel to the io panel
		ioPanel.add(departuresPanel);
	}

	/**
	 * Adds Stations to the "to" combo box.
	 */
	private void setToComboBoxItems() {
		toComboBox.removeAllItems();
		if (fromComboBox.getSelectedItem() != null) {
		    String fromStationString = fromComboBox.getSelectedItem().toString();
		    Station fromStation = transportNetwork.getStation(fromStationString);
	        ArrayList<Connection> connections = fromStation.getConncections();
	        for (Connection connection : connections) {
	            Station toStation = connection.getNeighborStation(fromStation);
	            toComboBox.addItem(toStation.getName());
	        }
		}
	}

	/**
	 * Action listener for the "from" combo box.<br>
	 * When changing the selection in the "from" combo box to another station
	 * it automatically changes the entries in the "to" combo box to the 
	 * neighbors of the station in the "from" combo box.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class fromComboBoxListener implements ActionListener {

		/**
		 * Adds other stations to the "to" combo box.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (toComboBox != null) {
				setToComboBoxItems();
			}
		}
	}

	/**
	 * Action listener for the "show" button.<br>
	 * When pressing the "show" button a new window will be opened. It displays 
	 * the departures and the line numbers from the selected station of the 
	 * "from" combo box to the selected station of the "to" combo box.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class ShowActionListener implements ActionListener {

		/**
		 * Opens a new window with departure information.
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (fromComboBox.getSelectedItem() != null && 
		            toComboBox.getSelectedItem() != null) {
		        ArrayList<DepartureItem> departureItems = 
	                transportNetwork.getDepartures(
	                        fromComboBox.getSelectedItem().toString(), 
	                        toComboBox.getSelectedItem().toString());
	            new DepartureWindow(fromComboBox.getSelectedItem().toString(), 
	                    toComboBox.getSelectedItem().toString(), departureItems);
		    }
		}
	}

	/**
	 * Creates an input panel in the middle of the input/output panel.<br>
	 * It contains the "form" fields for the timetable calculation.
	 */
	private void createInputPanel() {

		// definition for the text- and layout format
		FlowLayout ioLabelLayout = new FlowLayout(FlowLayout.LEFT);
		ioLabelLayout.setVgap(0);
		Font font = new Font("arial", 0, 14);

		// creates the input panel and sets its layout
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

		// Sets the size and alignment of the input panel
		inputPanel.setPreferredSize(new Dimension(440, 180));
		inputPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		// adds a title label to the input panel
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(ioLabelLayout);
		JLabel titleLabel = new JLabel();
		titleLabel.setPreferredSize(new Dimension(375, 30));
		titleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		titleLabel.setFont(new Font("arial", 1, 18));
		titleLabel.setText(" Timetable");
		titlePanel.add(titleLabel);
		inputPanel.add(titlePanel);

		// adds a "from" text field to the input panel
		JPanel fromPanel = new JPanel();
		fromPanel.setLayout(ioLabelLayout);
		JLabel fromLabel = new JLabel(" From:");
		fromLabel.setPreferredSize(new Dimension(50, 10));
		fromLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		fromLabel.setFont(font);
		fromTextFieldValue = "Enter the departure station";
		fromTextField = new JTextField(fromTextFieldValue, 29);
		fromTextField.setFont(font);
		fromTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		fromPanel.add(fromLabel);
		fromPanel.add(fromTextField);
		inputPanel.add(fromPanel);

		// adds a "to" text field to the input panel
		JPanel toPanel = new JPanel();
		toPanel.setLayout(ioLabelLayout);
		JLabel toLabel = new JLabel(" To:");
		toLabel.setFont(font);
		toLabel.setPreferredSize(new Dimension(50, 10));
		toLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		toTextFieldValue = "Enter the destination station";
		toTextField = new JTextField(toTextFieldValue, 29);
		toTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		toTextField.setFont(font);
		toPanel.add(toLabel);
		toPanel.add(toTextField);
		inputPanel.add(toPanel);

		// adds text field for the travel time to the input panel
		JPanel timePanel = new JPanel();
		timePanel.setLayout(ioLabelLayout);
		JLabel timeLabel = new JLabel(" Time:");
		timeLabel.setFont(font);
		timeLabel.setPreferredSize(new Dimension(50, 10));
		timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		timeTextFieldValue = "Enter the departure time 'hh:mm'";
		timeTextField = new JTextField(timeTextFieldValue, 29);
		timeTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		timeTextField.setFont(font);
		timePanel.add(timeLabel);
		timePanel.add(timeTextField);
		inputPanel.add(timePanel);

		// adds a "search" and a "clear" button to the input panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(ioLabelLayout);
		searchButton = new JButton("Search");
		searchButton.setPreferredSize(new Dimension(90, 25));
		searchButton.setBackground(Color.LIGHT_GRAY);
		searchButton.addActionListener(new SearchActionListener());
		clearButton = new JButton("Clear");
		clearButton.setPreferredSize(new Dimension(90, 25));
		clearButton.setBackground(Color.LIGHT_GRAY);
		clearButton.addActionListener(new ClearActionListener());
		buttonPanel.add(searchButton);
		buttonPanel.add(clearButton);
		inputPanel.add(buttonPanel);

		// adds the input panel to the io panel
		ioPanel.add(inputPanel);
	}

	/**
	 * Action listener for the "clear" button.<br>
	 * When pressing the "clear" button the "from"-, "to"- and the "time"- text 
	 * fields will be reseted to the default values.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class ClearActionListener implements ActionListener {

		/**
		 * Clears the search fields
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == clearButton) {
				fromTextField.setText(fromTextFieldValue);
				toTextField.setText(toTextFieldValue);
				timeTextField.setText(timeTextFieldValue);
			}
		}
	}

	/**
	 * Action listener for the "search" button.<br>
	 * When pressing the "search" button, the best route from one to the other
	 * station at a particular time will be calculated.<br>
	 * The fields will be validated and the calculation initialized. After the 
	 * result will be displayed in the output panel in a table.
	 *
	 * @author      Ruben Knaus, Dieu P. Van
	 * @version     1.0
	 *
	 */
	private class SearchActionListener implements ActionListener {

		/**
		 * search button
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == searchButton) {

				boolean errors = false;
				String errorMsgTitle = "Ooops, something is wrong!";
				String errorMsgText = "";

				// if the "from" text field contains no input
				if (fromTextField.getText().contains(fromTextFieldValue) 
						|| fromTextField.getText().isEmpty()) {
					errors = true;
					errorMsgText = 
						errorMsgText + "Please enter the departure station!\n";
					fromTextField.setText(fromTextFieldValue);

				// if the "from" text field contains no valid input
				} else if (transportNetwork.getStation(
						fromTextField.getText()) == null) {
					errors = true;
					errorMsgText = errorMsgText + 
							"Please enter a valid departure station!\n";
					fromTextField.setText(fromTextFieldValue);
				}

				// if the "to" text field contains no input
				if (toTextField.getText().contains(toTextFieldValue) 
						|| toTextField.getText().isEmpty()) {
					errors = true;
					errorMsgText = 
						errorMsgText + "Please enter the destination station!\n";
					toTextField.setText(toTextFieldValue);

				// if the "to" text field contains no valid input
				} else if (transportNetwork.getStation(
						toTextField.getText()) == null) {
					errors = true;
					errorMsgText = errorMsgText + 
							"Please enter a valid destination station!\n";
					toTextField.setText(toTextFieldValue);
				}

				// if the "time" text field contains no input
				String time = timeTextField.getText();
				if (timeTextField.getText().contains(timeTextFieldValue) 
						|| timeTextField.getText().isEmpty()) {
					errors = true;
					errorMsgText = errorMsgText + 
							"Please enter the departure time 'hh:mm'!\n";
					timeTextField.setText(timeTextFieldValue);

			    // if the "time" text field contains no valid input
				} else if (time.length() != 5) {
					errors = true;
					errorMsgText = errorMsgText + 
							"Please enter a valid departure time 'hh:mm'!\n";
					timeTextField.setText(timeTextFieldValue);
				} else {
					boolean timeError = false;
					char char0 = time.charAt(0);
					char char1 = time.charAt(1);
					char char2 = time.charAt(2);
					char char3 = time.charAt(3);
					char char4 = time.charAt(4);
					if (!(Character.isDigit(char0) && Character.isDigit(char1) &&
							Character.isDigit(char3) && 
							Character.isDigit(char4))) {
						timeError = true;
					}
					if (char2 != ':') {
						timeError = true;
					}
					if (timeError == false) {
						int hours = Integer.parseInt(time.substring(0, 2));
						int minutes = Integer.parseInt(time.substring(3, 5));
						if (hours >= 24 || minutes >= 60) {
							timeError = true;
						}
					}
					if (timeError == true) {
						errors = true;
						errorMsgText = errorMsgText + 
								"Please enter a valid departure time 'hh:mm'!\n";
						timeTextField.setText(timeTextFieldValue);
					}
				}

				// if from and to contain the same input
				if(fromTextField.getText().equals(toTextField.getText())) {
					errors = true;
					errorMsgText = errorMsgText + "Please enter a different "
					+ "stations for the departure and the destination!\n";
					fromTextField.setText(fromTextFieldValue);
					toTextField.setText(toTextFieldValue);
				}

				// displays error message if wrong input got detected
				if (errors == true) {
					JOptionPane.showMessageDialog(null, errorMsgText, 
							errorMsgTitle, JOptionPane.ERROR_MESSAGE);
					errorMsgText = "";
					errors = false;

			    // if all inputs are valid, search for the best connection
				} else {
					Station source = 
						transportNetwork.getStation(fromTextField.getText());
					Station destination = 
						transportNetwork.getStation(toTextField.getText());
					Calendar departure = Calendar.getInstance();
					int hours = Integer.parseInt(time.substring(0, 2));
					int minutes = Integer.parseInt(time.substring(3, 5));
					departure.set(0, 0, 0, hours, minutes);
					stopoverList.clear();
					stopoverList.addAll(routeCalculator.calculateRoute(source, 
							destination, departure));
					mapPanel.repaint();
					stopoverTable.repaint();
				}
			}  // end of if "search" button
		}  // end of function
	} // end of inner class

	/**
	 * Creates an output panel at the bottom of the input/output panel.<br>
	 * It contains the result of the timetable calculation.
	 */
	private void createOutputPanel() {

		// creates the output panel and sets its layout
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		// Sets the size and alignment of the output panel
		outputPanel.setPreferredSize(new Dimension(440, 400));
		outputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		// adds a title label to the output panel
		JLabel titleLabel = new JLabel(" Connection");
		titleLabel.setPreferredSize(new Dimension(375, 30));
		titleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		titleLabel.setFont(new Font("arial", 1, 18));
		outputPanel.add(titleLabel);

		// adds the the table to the tablePanel
		JPanel tablePanel = new JPanel();
		JScrollPane tablePane = new JScrollPane(stopoverTable);
		tablePane.setPreferredSize(new Dimension(410, 285));
		tablePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		tablePanel.add(tablePane);

		// adds the the tablePanel to the outputPanel
		outputPanel.add(tablePanel);

		// adds the output panel to the io panel
		ioPanel.add(outputPanel);
	}

	/**
	 * Creates a map of the transport network and displays it on the right side
	 * (center) of the GUI.
	 */
	private void createMapPanel() {

		// sets the size and alignment of the map panel
		int panelWidth = 760;
		int panelHeight = 700;

		// creates the map panel and sets its layout
		mapPanel = new MapPanel(panelWidth, panelHeight, 
				transportNetwork.getStationList(), 
				transportNetwork.getConnectionList(), stopoverList);
		mapPanel.setLayout(null);
		mapPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));

		// adds the map Panel to the frame
		frame.getContentPane().add(BorderLayout.CENTER, mapPanel);
	}
}
