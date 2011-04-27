package ch.netgeek.travelmaster.route;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class TransportNetworkTest {

	private TransportNetwork test;
    private Station stationA = new Station("stationA");
    private Station stationB = new Station("stationB");
    private Station station = new Station("station");
    
    @Before
    public void setUpTransportNetwork(){
    	test = new TransportNetwork();
    }
    
    @Test
    public void testTransportNetwork() {
        TransportNetwork test = new TransportNetwork();
        test.getStation("test");
        test.getLines();
        test.addConnection(stationA, stationB, 4);
        assertTrue(test.getStation("test") == null);
        assertTrue(test.getLines().isEmpty() == true);
    }

    @Test
    public void testAddStation() {
        test.addStation("Hollywood");
        assertTrue(test.getStation("Hollywood") != null);     
    }

    @Test
    public void testAddConnection() {
        test.addConnection(stationA, stationB, 4);
    }

    @Test
    public void testAddLine() {
        TransportNetwork test = new TransportNetwork();
        ArrayList<Station> stations = new ArrayList<Station>();
        ArrayList<Connection> connections = new ArrayList<Connection>();
        ArrayList<Calendar> departuresFirstStation = new ArrayList<Calendar>();
        ArrayList<Calendar> departuresLastStation = new ArrayList<Calendar>();
        ArrayList<Line> lines = new ArrayList<Line>();
        
        Station station1 = new Station("1. Station");
        Station station2 = new Station("2. Station");
        Station station3 = new Station("3. Station");
        Station station4 = new Station("4. Station");
        Station station5 = new Station("5. Station");
        Station station6 = new Station("6. Station");
        Connection connection1 = new Connection(station6, station5, 10);
        Connection connection2 = new Connection(station1, station2, 4);
        Connection connection3 = new Connection(station5, station4, 12);
        Connection connection4 = new Connection(station3, station4, 7);
        Connection connection5 = new Connection(station2, station3, 9);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        Line line01 = new Line(3, "Metro");
        Line line02 = new Line(3, "Metro");
        Line line03 = new Line(3, "Metro");
        Line line04 = new Line(3, "Metro");
        Line line05 = new Line(3, "Metro");
        Line line06 = new Line(3, "Metro");
        Line line07 = new Line(3, "Metro");
        Line line08 = new Line(3, "Metro");
        Line line09 = new Line(3, "Metro");
        Line line10 = new Line(3, "Metro");
        Line line11b = new Line(3, "Metro");
        Line line12b = new Line(3, "Metro");
        Line line13b = new Line(3, "Metro");
        Line line14b = new Line(3, "Metro");
        Line line15b = new Line(3, "Metro");
        Line line16b = new Line(3, "Metro");
        Line line17b = new Line(3, "Metro");
        Line line18b = new Line(3, "Metro");
        Line line19b = new Line(3, "Metro");
        Line line20b = new Line(3, "Metro");
        Calendar lineC01 = Calendar.getInstance();
        Calendar lineC02 = Calendar.getInstance();
        Calendar lineC03 = Calendar.getInstance();
        Calendar lineC04 = Calendar.getInstance();
        Calendar lineC05 = Calendar.getInstance();
        Calendar lineC06 = Calendar.getInstance();
        Calendar lineC07 = Calendar.getInstance();
        Calendar lineC08 = Calendar.getInstance();
        Calendar lineC09 = Calendar.getInstance();
        Calendar lineC10 = Calendar.getInstance();
        Calendar lineC11b = Calendar.getInstance();
        Calendar lineC12b = Calendar.getInstance();
        Calendar lineC13b = Calendar.getInstance();
        Calendar lineC14b = Calendar.getInstance();
        Calendar lineC15b = Calendar.getInstance();
        Calendar lineC16b = Calendar.getInstance();
        Calendar lineC17b = Calendar.getInstance();
        Calendar lineC18b = Calendar.getInstance();
        Calendar lineC19b = Calendar.getInstance();
        Calendar lineC20b = Calendar.getInstance();
        
        lineC01.set(0, 0, 0, 15, 7);
        lineC02.set(0, 0, 0, 15, 16);
        lineC03.set(0, 0, 0, 15, 23);
        lineC04.set(0, 0, 0, 15, 35);
        lineC05.set(0, 0, 0, 15, 45);
        lineC06.set(0, 0, 0, 15, 37);
        lineC07.set(0, 0, 0, 15, 46);
        lineC08.set(0, 0, 0, 15, 53);
        lineC09.set(0, 0, 0, 16, 5);
        lineC10.set(0, 0, 0, 16, 15);
        
        lineC11b.set(0, 0, 0, 15, 22);
        lineC12b.set(0, 0, 0, 15, 31);
        lineC13b.set(0, 0, 0, 15, 38);
        lineC14b.set(0, 0, 0, 15, 50);
        lineC15b.set(0, 0, 0, 16, 00);
        lineC16b.set(0, 0, 0, 15, 52);
        lineC17b.set(0, 0, 0, 16, 1);
        lineC18b.set(0, 0, 0, 16, 8);
        lineC19b.set(0, 0, 0, 16, 20);
        lineC20b.set(0, 0, 0, 16, 30);
        
        line01.addDeparture(station1, station2, lineC01);
        line02.addDeparture(station2, station3, lineC02);
        line03.addDeparture(station3, station4, lineC03);
        line04.addDeparture(station4, station5, lineC04);
        line05.addDeparture(station5, station6, lineC05);
        line06.addDeparture(station1, station2, lineC06);
        line07.addDeparture(station2, station3, lineC07);
        line08.addDeparture(station3, station4, lineC08);
        line09.addDeparture(station4, station5, lineC09);
        line10.addDeparture(station5, station6, lineC10);
        line11b.addDeparture(station6, station5, lineC11b);
        line12b.addDeparture(station5, station4, lineC12b);
        line13b.addDeparture(station4, station3, lineC13b);
        line14b.addDeparture(station3, station2, lineC14b);
        line15b.addDeparture(station2, station1, lineC15b);
        line16b.addDeparture(station6, station5, lineC16b);
        line17b.addDeparture(station5, station4, lineC17b);
        line18b.addDeparture(station4, station3, lineC18b);
        line19b.addDeparture(station3, station2, lineC19b);
        line20b.addDeparture(station2, station1, lineC20b);

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
        connections.add(connection1);
        connections.add(connection2);
        connections.add(connection3);
        connections.add(connection4);
        connections.add(connection5);      
        c1.set(0, 0, 0, 15, 03);
        c2.set(0, 0, 0, 15, 33);
        c3.set(0, 0, 0, 15, 18);
        c4.set(0, 0, 0, 15, 48);
        departuresFirstStation.add(c1);
        departuresFirstStation.add(c2);
        departuresLastStation.add(c3);
        departuresLastStation.add(c4);
        
        lines.add(line01);
        lines.add(line02);
        lines.add(line03);
        lines.add(line04);
        lines.add(line05);
        lines.add(line06);
        lines.add(line07);
        lines.add(line08);
        lines.add(line09);
        lines.add(line10);
        lines.add(line11b);
        lines.add(line12b);
        lines.add(line13b);
        lines.add(line14b);
        lines.add(line15b);
        lines.add(line16b);
        lines.add(line17b);
        lines.add(line18b);
        lines.add(line19b);
        lines.add(line20b);
   
        test.addLine(3, "Metro", stations, departuresFirstStation, 
        		departuresLastStation);
        assertTrue(test.getLines() != null);
    }

    @Test
    public void testGetStation() {
        test.getStation("station");
        HashMap<String, Station> stations = new HashMap<String, Station>();
        stations.put("station", station);
        assertEquals(test.getStation("station"), stations.get(station));
    }

    @Test
    public void testGetStationList() {
    	test.addStation("station");
        ArrayList<Station> stationList = new ArrayList<Station>();
        stationList.add(station);
        assertEquals(test.getStationList(), stationList);
    }

    @Test
    public void testGetNeighborStationList() {
        Connection connection = new Connection(stationA, stationB, 4);
        stationA.addConnection(connection);
        stationB.addConnection(connection);
    	ArrayList<Station> neighborStationList = new ArrayList<Station>();
    	neighborStationList.add(stationB);
        assertEquals(neighborStationList, test.getNeighborStationList(stationA));
    }
    
    @Test
    public void testGetConnectionList() {
    	Connection connection = new Connection(stationA, stationB, 10);
    	test.addConnection(stationA, stationB, 10);
        ArrayList<Connection> connectionList = new ArrayList<Connection>();
        connectionList.add(connection);
        assertEquals(connectionList, test.getConnectionList());
    }

    @Test
    public void testGetLines() {
        TransportNetwork test = new TransportNetwork();
        ArrayList<Station> stations = new ArrayList<Station>();
        ArrayList<Connection> connections = new ArrayList<Connection>();
        ArrayList<Calendar> departuresFirstStation = new ArrayList<Calendar>();
        ArrayList<Calendar> departuresLastStation = new ArrayList<Calendar>();
        ArrayList<Line> lines = new ArrayList<Line>();
        
        Station station1 = new Station("1. Station");
        Station station2 = new Station("2. Station");
        Station station3 = new Station("3. Station");
        Station station4 = new Station("4. Station");
        Station station5 = new Station("5. Station");
        Station station6 = new Station("6. Station");
        Connection connection1 = new Connection(station6, station5, 10);
        Connection connection2 = new Connection(station1, station2, 4);
        Connection connection3 = new Connection(station5, station4, 12);
        Connection connection4 = new Connection(station3, station4, 7);
        Connection connection5 = new Connection(station2, station3, 9);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Calendar c3 = Calendar.getInstance();
        Calendar c4 = Calendar.getInstance();
        Line line01 = new Line(3, "Metro");
        Line line02 = new Line(3, "Metro");
        Line line03 = new Line(3, "Metro");
        Line line04 = new Line(3, "Metro");
        Line line05 = new Line(3, "Metro");
        Line line06 = new Line(3, "Metro");
        Line line07 = new Line(3, "Metro");
        Line line08 = new Line(3, "Metro");
        Line line09 = new Line(3, "Metro");
        Line line10 = new Line(3, "Metro");
        Line line11b = new Line(3, "Metro");
        Line line12b = new Line(3, "Metro");
        Line line13b = new Line(3, "Metro");
        Line line14b = new Line(3, "Metro");
        Line line15b = new Line(3, "Metro");
        Line line16b = new Line(3, "Metro");
        Line line17b = new Line(3, "Metro");
        Line line18b = new Line(3, "Metro");
        Line line19b = new Line(3, "Metro");
        Line line20b = new Line(3, "Metro");
        Calendar lineC01 = Calendar.getInstance();
        Calendar lineC02 = Calendar.getInstance();
        Calendar lineC03 = Calendar.getInstance();
        Calendar lineC04 = Calendar.getInstance();
        Calendar lineC05 = Calendar.getInstance();
        Calendar lineC06 = Calendar.getInstance();
        Calendar lineC07 = Calendar.getInstance();
        Calendar lineC08 = Calendar.getInstance();
        Calendar lineC09 = Calendar.getInstance();
        Calendar lineC10 = Calendar.getInstance();
        Calendar lineC11b = Calendar.getInstance();
        Calendar lineC12b = Calendar.getInstance();
        Calendar lineC13b = Calendar.getInstance();
        Calendar lineC14b = Calendar.getInstance();
        Calendar lineC15b = Calendar.getInstance();
        Calendar lineC16b = Calendar.getInstance();
        Calendar lineC17b = Calendar.getInstance();
        Calendar lineC18b = Calendar.getInstance();
        Calendar lineC19b = Calendar.getInstance();
        Calendar lineC20b = Calendar.getInstance();
        
        lineC01.set(0, 0, 0, 15, 7);
        lineC02.set(0, 0, 0, 15, 16);
        lineC03.set(0, 0, 0, 15, 23);
        lineC04.set(0, 0, 0, 15, 35);
        lineC05.set(0, 0, 0, 15, 45);
        lineC06.set(0, 0, 0, 15, 37);
        lineC07.set(0, 0, 0, 15, 46);
        lineC08.set(0, 0, 0, 15, 53);
        lineC09.set(0, 0, 0, 16, 5);
        lineC10.set(0, 0, 0, 16, 15);
        
        lineC11b.set(0, 0, 0, 15, 22);
        lineC12b.set(0, 0, 0, 15, 31);
        lineC13b.set(0, 0, 0, 15, 38);
        lineC14b.set(0, 0, 0, 15, 50);
        lineC15b.set(0, 0, 0, 16, 00);
        lineC16b.set(0, 0, 0, 15, 52);
        lineC17b.set(0, 0, 0, 16, 1);
        lineC18b.set(0, 0, 0, 16, 8);
        lineC19b.set(0, 0, 0, 16, 20);
        lineC20b.set(0, 0, 0, 16, 30);
        
        line01.addDeparture(station1, station2, lineC01);
        line02.addDeparture(station2, station3, lineC02);
        line03.addDeparture(station3, station4, lineC03);
        line04.addDeparture(station4, station5, lineC04);
        line05.addDeparture(station5, station6, lineC05);
        line06.addDeparture(station1, station2, lineC06);
        line07.addDeparture(station2, station3, lineC07);
        line08.addDeparture(station3, station4, lineC08);
        line09.addDeparture(station4, station5, lineC09);
        line10.addDeparture(station5, station6, lineC10);
        line11b.addDeparture(station6, station5, lineC11b);
        line12b.addDeparture(station5, station4, lineC12b);
        line13b.addDeparture(station4, station3, lineC13b);
        line14b.addDeparture(station3, station2, lineC14b);
        line15b.addDeparture(station2, station1, lineC15b);
        line16b.addDeparture(station6, station5, lineC16b);
        line17b.addDeparture(station5, station4, lineC17b);
        line18b.addDeparture(station4, station3, lineC18b);
        line19b.addDeparture(station3, station2, lineC19b);
        line20b.addDeparture(station2, station1, lineC20b);

        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        stations.add(station4);
        stations.add(station5);
        stations.add(station6);
        connections.add(connection1);
        connections.add(connection2);
        connections.add(connection3);
        connections.add(connection4);
        connections.add(connection5);      
        c1.set(0, 0, 0, 15, 03);
        c2.set(0, 0, 0, 15, 33);
        c3.set(0, 0, 0, 15, 18);
        c4.set(0, 0, 0, 15, 48);
        departuresFirstStation.add(c1);
        departuresFirstStation.add(c2);
        departuresLastStation.add(c3);
        departuresLastStation.add(c4);
        
        lines.add(line01);
        lines.add(line02);
        lines.add(line03);
        lines.add(line04);
        lines.add(line05);
        lines.add(line06);
        lines.add(line07);
        lines.add(line08);
        lines.add(line09);
        lines.add(line10);
        lines.add(line11b);
        lines.add(line12b);
        lines.add(line13b);
        lines.add(line14b);
        lines.add(line15b);
        lines.add(line16b);
        lines.add(line17b);
        lines.add(line18b);
        lines.add(line19b);
        lines.add(line20b);
       
        test.addLine(3, "Metro", stations, departuresFirstStation, 
        		departuresLastStation);
        assertEquals(test.getLines(), lines);
    }

}
