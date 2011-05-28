package ch.netgeek.travelmaster.gui;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import ch.netgeek.travelmaster.route.Station;
import ch.netgeek.travelmaster.route.TransportNetwork;

public class MapPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -53021081188685247L;
    
    private int panelWidth;
    private int panelHeight;
    private TransportNetwork transportNetwork;
    
    public MapPanel(int panelWidth, int panelHeight, TransportNetwork transportNetwork) {
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.transportNetwork = transportNetwork;
    }
    
    private void paintStations(Graphics g) {

        System.out.println("Panel Width: "  + panelWidth);
        System.out.println("Panel Height: " + panelHeight);
        
        ArrayList<Station> stationList = transportNetwork.getStationList();
        int width = 130;
        int height = 30;
        for (Station station : stationList) {
            String name = station.getName();
            int x = (station.getXPos() * (panelWidth / 100)) - (width / 2);
            int y = (station.getYPos() * (panelHeight / 100)) - (height / 2);
            g.drawRoundRect(x, y, width, height, 5, 5);
            System.out.println("Station: " + name + " x: " + x + " y: " + y);
        }
//        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawLine(0, 10, 760, 10);
    }
    
    private void paintConnections(Graphics g) {
        
    }
    
    @Override
    protected void paintComponent(Graphics g ) {
        super.paintComponent(g);
        paintStations(g);
    }

}
