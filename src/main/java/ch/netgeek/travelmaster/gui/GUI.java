package ch.netgeek.travelmaster.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import ch.netgeek.travelmaster.algorithm.RouteCalculator;
import ch.netgeek.travelmaster.algorithm.Stopover;
import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TransportNetwork;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * This class represents the TravelMaster GUI.<br>
 * It is divided into three areas:<br>
 * - The input area where the user can enter the time table requests<br>
 * - The output area where the time table gets displayed<br>
 * - The map of the transport network as an overview for the user
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     0.1
 *
 */
public class GUI {

    // general variables declaration
    private TransportNetwork transportNetwork;
    private RouteCalculator routeCalculator;
    private TableModel tableModel;

    // GUI variables declaration
    private JFrame frame;
    private JPanel bannerPanel;
    private JPanel ioPanel;
    private MapPanel mapPanel;

    /**
     * Initializes the GUI.
     * 
     * @param transportNetwork      The transport network
     * @param routeCalculator       The route calculator
     */
    public GUI(TransportNetwork transportNetwork, RouteCalculator routeCalculator) {
        this.transportNetwork = transportNetwork;
        this.routeCalculator = routeCalculator;
//        this.tableModel = tableModel;

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

        // Generating a menu bar (JMenuBar) and adding it to the window (JFrame)
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Generates a menu (JMenu) and add it to the menu bar (JMenuBar)
        JMenu fileMenu = new JMenu("File");
        fileMenu.setFont(new Font("arial", 0, 12));
        menuBar.add(fileMenu);

        // Generates menu items (JMenuItem) and adds them to the menu (JMenu)
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
     * Action listener for the about menu entry.<br>
     * It displays a popup window with some information about the software.
     *
     * @author      Ruben Knaus, Dieu P. Van
     * @version     0.1
     *
     */
    private class AboutActionListener implements ActionListener {

        /**
         * Performed action when pressing the about menu item.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Add popup window here

        }
    }

    /**
     * Action listener for the exit menu entry.<br>
     * It closes the program.
     *
     * @author      Ruben Knaus, Dieu P. Van
     * @version     0.1
     *
     */
    private class ExitActionListener implements ActionListener {

        /**
         * Performed action when pressing the exit menu item.
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
        bannerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
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
     * It displays the input mask for the time table calculation (upper half) 
     * and the result of the time table calculation (lower half)
     */
    private void createIOPanel() {

        // creates the input/output panel and sets its layout
        ioPanel = new JPanel();
        ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.Y_AXIS));
        ioPanel.setPreferredSize(new Dimension(440, 700));
        ioPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        ioPanel.setAlignmentY(JPanel.TOP_ALIGNMENT);

        // creates the input panels and adds it to the input/output panel
        createInputPanel();

        // creates the input panels and adds it to the input/output panel
        createOutputPanel();

        // adds the IO Panel to the frame
        frame.getContentPane().add(BorderLayout.WEST, ioPanel);
    }

    /**
     * Creates an input panel at the top of the input/output panel.<br>
     * It contains the form fields for the time table calculation.
     */
    private void createInputPanel() {

        // creates the input panel and sets its layout
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Sets the size and alignement of the input panel
        inputPanel.setPreferredSize(new Dimension(440, 300));
        inputPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);

        // adds a title label to the input panel
        JLabel titleLabel = new JLabel("Timetable");
        titleLabel.setPreferredSize(new Dimension(375, 30));
        titleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        titleLabel.setFont(new Font("arial", 1, 16));
        inputPanel.add(titleLabel);

        // adds a from textfield to the input panel
        JLabel fromLabel = new JLabel("Von: ");
        fromLabel.setPreferredSize(new Dimension(60, 10));
        fromLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(fromLabel);
        JTextField fromTextField = new JTextField("Abfahrtsort", 32);
        fromTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(fromTextField);

        // adds a to textfield to the input panel
        JLabel toLabel = new JLabel("Nach: ");
        toLabel.setPreferredSize(new Dimension(60, 10));
        toLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(toLabel);
        JTextField toTextField = new JTextField("Zielort", 32);
        toTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(toTextField);

        // adds textfields for the travel time to the input panel
        JLabel timeLabel = new JLabel("Abfahrt: ");
        timeLabel.setPreferredSize(new Dimension(60, 10));
        timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(timeLabel);
        JTextField timeTextField = new JTextField("hh:mm", 5);
        timeTextField.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        inputPanel.add(timeTextField);

        // adds a blank label to force a line break in the input panel
        JLabel blankLabel = new JLabel();
        blankLabel.setPreferredSize(new Dimension(280, 10));
        inputPanel.add(blankLabel);

        // adds a search button to the input panel
        JButton searchButton = new JButton("Search");
        searchButton.setPreferredSize(new Dimension(90, 25));
        searchButton.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(searchButton);

        // adds a clear button to the input panel
        JButton clearButton = new JButton("Clear");
        clearButton.setPreferredSize(new Dimension(90, 25));
        clearButton.setBackground(Color.LIGHT_GRAY);
        inputPanel.add(clearButton);

        // adds the input panel to the io panel
        ioPanel.add(inputPanel);
    }

    /**
     * Creates an output panel at the bottom of the input/output panel.<br>
     * It contains the result of the time table calculation.
     */
    private void createOutputPanel() {

        // creates the output panel and sets its layout
        JPanel outputPanel = new JPanel();
        outputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Sets the size and alignement of the output panel
        outputPanel.setPreferredSize(new Dimension(440, 400));
        outputPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // adds a title label to the output panel
        JLabel titleLabel = new JLabel("Connection");
        titleLabel.setPreferredSize(new Dimension(375, 30));
        titleLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
        titleLabel.setFont(new Font("arial", 1, 16));
        outputPanel.add(titleLabel);
        
        // creates a table
//        JTable table = new JTable(tableModel);
//        TableColumn cStations = 
//        	table.getColumnModel().getColumn(0);
//        TableColumn cLines = 
//        	table.getColumnModel().getColumn(1);
//        TableColumn cDeparture = 
//        	table.getColumnModel().getColumn(2);
//        TableColumn cArrival = 
//        	table.getColumnModel().getColumn(3);
//        TableColumn cDuration = 
//        	table.getColumnModel().getColumn(4);
//
//        // sets the titles of the columns
//        cStations.setHeaderValue("Station");
//        cLines.setHeaderValue("Linie");
//        cDeparture.setHeaderValue("Abfahrt");
//        cArrival.setHeaderValue("Ankunft");
//        cDuration.setHeaderValue("Reisedauer");

        // adds the output panel to the io panel
        ioPanel.add(outputPanel);
    }

    /**
     * Creates a map of the transport network and displays it on the left side
     * (center) of the GUI.
     */
    private void createMapPanel() {
        
        // sets the size and allignement of the map panel
        int panelWidth = 760;
        int panelHeight = 700;

        // creates the map panel and sets its layout
        mapPanel = new MapPanel();
        mapPanel.setLayout(null);

        
        mapPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        mapPanel.setBackground(Color.WHITE);
        
        mapPanel.repaint();
        
//
//        // Creates the transport network map and adds it to the map panel
//        ArrayList<Station> stationList = transportNetwork.getStationList();
//        for (Station station : stationList) {
//            String name = station.getName();
//            int x = station.getXPos() * (panelWidth / 100);
//            int y = station.getYPos() * (panelHeight / 100);
//            int width = 130;
//            int height = 30;
//            JPanel stationPanel = new JPanel();
//            stationPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
//            stationPanel.setAlignmentY(JPanel.CENTER_ALIGNMENT);
//            stationPanel.setBounds(x, y, width, height);
//            JLabel stationLabel = new JLabel(name);
//            stationPanel.add(stationLabel);
//            mapPanel.add(stationPanel);
//        }

        // adds the map Panel to the frame
        frame.getContentPane().add(BorderLayout.CENTER, mapPanel);
    }
    
    private class MapPanel extends JPanel {
        
        private static final long serialVersionUID = -5402738780711033582L;
        
        private int panelWidth = this.getWidth();
        private int panelHeight = this.getHeight();
        

        private void paintStations(Graphics g) {

            System.out.println("Panel Width: "  + panelWidth);
            System.out.println("Panel Height: " + panelHeight);
            
            ArrayList<Station> stationList = transportNetwork.getStationList();
            int width = 130;
            int height = 30;
            for (Station station : stationList) {
                String name = station.getName();
                int x = station.getXPos() * (panelWidth / 100) - width / 2;
                int y = station.getYPos() * (panelHeight / 100) - height / 2;
                g.drawRoundRect(x, y, width, height, 5, 5);
                System.out.println("Station: " + name + " x: " + x + " y: " + y);
            }
//            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.drawLine(10, 10, 100, 50);
        }
        
        private void paintConnections(Graphics g) {
            
        }
        
        @Override
        protected void paintComponent(Graphics g ) {
            super.paintComponent(g);
            paintStations(g);
        }
        
    }
}
