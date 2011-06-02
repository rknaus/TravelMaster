package ch.netgeek.travelmaster.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.netgeek.travelmaster.route.DepartureItem;

/**
 * Displays a separate window displaying all departures of a station to a 
 * destination station
 * 
 * @author      Dieu P. Van, Ruben Knaus
 * @version     0.1
 */
public class DepartureWindow {
    
    // variables declaration
    private String source;
    private String destination;
    private ArrayList<DepartureItem> departureItems;
    
    // GUI variables declaration
    private JFrame frame;

    /**
     * Initializes the Departure Window
     * 
     * @param source                The source Station name
     * @param detination            The destination station name
     * @param departureItems        The departure list
     */
    public DepartureWindow(String source, String destination, 
            ArrayList<DepartureItem> departureItems) {
        this.source = source;
        this.destination = destination;
        this.departureItems = departureItems;
        
        // creates the frame
        createFrame();
    }
    
    /**
     * Creates the departure list window
     */
    private void createFrame() {
        
        frame = new JFrame("Departures");
        
        // Adding a title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        titlePanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        titlePanel.setPreferredSize(new Dimension(200, 50));
        JLabel titleLabel1 = new JLabel();
        titleLabel1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel1.setFont(new Font("arial", 1, 14));
        titleLabel1.setText("Departures");
        titlePanel.add(titleLabel1);
        JLabel titleLabel2 = new JLabel();
        titleLabel2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel2.setFont(new Font("arial", 1, 14));
        titleLabel2.setText("from " + source);
        titlePanel.add(titleLabel2);
        JLabel titleLabel3 = new JLabel();
        titleLabel3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        titleLabel3.setFont(new Font("arial", 1, 14));
        titleLabel3.setText("to " + destination);
        titlePanel.add(titleLabel3);
        frame.getContentPane().add(BorderLayout.NORTH, titlePanel);
        
        // adding the departures list
        JPanel departurePanel = new JPanel();
        departurePanel.setLayout(new BoxLayout(departurePanel, BoxLayout.Y_AXIS));
        departurePanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        departurePanel.setAlignmentY(JPanel.TOP_ALIGNMENT);
        Color bgColor = Color.WHITE;
        for (DepartureItem departureItem : departureItems) {
            JPanel departureItemPanel = new JPanel();
            departureItemPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            departureItemPanel.setBackground(bgColor);
            
            // the departure time
            JLabel timeLabel = new JLabel();
            timeLabel.setPreferredSize(new Dimension(50, 10));
            timeLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            int hour = departureItem.getHour();
            int minute = departureItem.getMinute();
            String hourString;
            if (hour < 10) {
                hourString = "0" + hour;
            } else {
                hourString = Integer.toString(hour);
            }
            String minuteString;
            if (minute < 10) {
                minuteString = "0" + minute;
            } else {
                minuteString = Integer.toString(minute);
            }
            String time = hourString + ":" + minuteString;
            timeLabel.setText(time);
            departureItemPanel.add(timeLabel);
            
            // the line
            JLabel lineLabel = new JLabel();
            lineLabel.setPreferredSize(new Dimension(100, 10));
            lineLabel.setAlignmentX(JLabel.LEFT_ALIGNMENT);
            lineLabel.setText("Line " + departureItem.getLine());
            departureItemPanel.add(lineLabel);
            departurePanel.add(departureItemPanel);
            
            // setting an alternating background color
            if (bgColor == Color.WHITE) {
                bgColor = new Color(225, 225, 225);
            } else {
                bgColor = Color.WHITE;
            }
        }
        JScrollPane scrollPane = new JScrollPane(departurePanel);
        scrollPane.setPreferredSize(new Dimension(200, 350));
        frame.getContentPane().add(BorderLayout.CENTER, scrollPane);
        
        // set parameters like size, visibility, etc.
        frame.setSize(200, 400);
        frame.setVisible(true);
    }
}
