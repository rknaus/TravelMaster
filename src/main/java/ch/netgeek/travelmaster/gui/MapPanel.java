package ch.netgeek.travelmaster.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import ch.netgeek.travelmaster.route.Connection;
import ch.netgeek.travelmaster.route.Station;

/**
 * This class paints the Transport Network Map into a JPanel
 *
 * @author      Ruben Knaus, Dieu P. Van
 * @version     0.1
 *
 */
public class MapPanel extends JPanel {

    // variables declaration
    private static final long serialVersionUID = -53021081188685247L;
    private int panelWidth;
    private int panelHeight;
    private ArrayList<Station> stationList;
    private ArrayList<Connection> connectionList;
    
    /**
     * Initializes the MapPanel.
     * 
     * @param panelWidth                    The panel width
     * @param panelHeight                   The panel height
     * @param stationList                   The stations list
     * @param connectionList                The connections list
     */
    public MapPanel(int panelWidth, int panelHeight, 
            ArrayList<Station> stationList,
            ArrayList<Connection> connectionList) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.stationList = stationList;
        this.connectionList = connectionList;
    }
    
    /**
     * Draws the Stations into the JPanel
     * 
     * @param g2                            The Graphics2D object
     */
    private void paintStations(Graphics2D g2) {
        int width = 130;
        int height = 30;
        BasicStroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(stroke);
        for (Station station : stationList) {
            String name = station.getName();
            int x = station.getXPos() * (panelWidth / 100);
            int y = station.getYPos() * (panelHeight / 100);
            int xRect = x - (width / 2);
            int yRect = y - (height / 2);
            int xFont = x - 50;
            int yFont = y + 5;
            g2.setColor(Color.DARK_GRAY);
            g2.fillRoundRect(xRect, yRect, width, height, 20, 20);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(xRect, yRect, width, height, 20, 20);
            g2.setColor(Color.WHITE);
            g2.drawString(name, xFont, yFont);
        }
    }
    
    /**
     * Draws the Connections into the JPanel
     * 
     * @param g2                            The Graphics2D object
     */
    private void paintConnections(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(stroke);
        for (Connection connection : connectionList) {
            int xA = connection.getStationA().getXPos() * (panelWidth / 100);
            int yA = connection.getStationA().getYPos() * (panelHeight / 100);
            int xB = connection.getStationB().getXPos() * (panelWidth / 100);
            int yB = connection.getStationB().getYPos() * (panelHeight / 100);
            g2.drawLine(xA, yA, xB, yB);
        }
    }
    
    /**
     * Main function which draws the content into the JPanel
     */
    @Override
    protected void paintComponent(Graphics g ) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        g2.setFont(font);
        paintConnections(g2);
        paintStations(g2);
    }

}
