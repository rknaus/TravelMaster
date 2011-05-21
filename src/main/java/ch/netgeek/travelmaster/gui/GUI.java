package ch.netgeek.travelmaster.gui;

import javax.swing.*;

import ch.netgeek.travelmaster.algorithm.RouteCalculator;
import ch.netgeek.travelmaster.route.TransportNetwork;

import java.awt.*;

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
    
    public GUI(TransportNetwork transportNetwork, RouteCalculator routeCalculator) {
        setFrames();
    }
	
	public void setFrames(){
		
		JFrame frame = new JFrame();
		JLabel lBlank = new JLabel();
		//Creates a menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem about = new JMenuItem("About TravelMaster");
		JMenuItem exit = new JMenuItem("Exit");
		menuBar.add(fileMenu);
		menuBar.setPreferredSize(new Dimension(20,25));
		about.setFont(new Font("arial",0,12));
		exit.setFont(new Font("arial",0,12));
		fileMenu.setFont(new Font("arial",0,12));
		fileMenu.add(about);
		fileMenu.add(exit);	
		
		// TODO Dieu: Continue here :-)
//		Action exitAction = new AbstractAction("Exit"){
//			public void actionPermormed(ActionEvent e){
//				System.exit(0);
//			}
//		}
//		
		//Creates a banner for the NORTH frame
		JPanel pBanner = new JPanel();
		pBanner.setLayout(new FlowLayout());
		//Creates a container for output/input mask
		JPanel pWest = new JPanel();
		pWest.setLayout(new BoxLayout(pWest, BoxLayout.Y_AXIS));
		//Creates a container for the input mask
		JPanel pInput = new JPanel();
		pInput.setLayout(new FlowLayout(0));
		//creates a container for the output
		JPanel pOutput = new JPanel();
		pOutput.setLayout(new FlowLayout(0));
		
		//Components for the NORTH frame
		JLabel lBanner = new JLabel(" TRAVELMASTER");
		lBanner.setFont(new Font("arial",1,30));
		lBanner.setForeground(Color.GRAY);
		lBanner.setPreferredSize(new Dimension(100,60));
		pBanner.add(lBanner);
		
		//Components for pInput
		pInput.setPreferredSize(new Dimension(440,0));
		pInput.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel lTitle = new JLabel("Timetable");
		lTitle.setPreferredSize(new Dimension(375,30));
		lTitle.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lTitle.setFont(new Font("arial",1,16));
		pInput.add(lTitle);
		
		JLabel lStart = new JLabel("Start:");
		lStart.setPreferredSize(new Dimension(60,10));
		lStart.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(lStart);
		
		JTextField tfStart = new JTextField(32);
		tfStart.setText("Abfahrtsort");
		tfStart.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(tfStart);
		
		JLabel lEnd = new JLabel("Ziel:");
		lEnd.setPreferredSize(new Dimension(60,10));
		lEnd.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(lEnd);
		
		JTextField tfEnd = new JTextField(32);
		tfEnd.setText("Zielort");
		tfEnd.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(tfEnd);
		
		JLabel lTime = new JLabel("Abfahrt:");
		lTime.setPreferredSize(new Dimension(60,10));
		lTime.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(lTime);
		
		JTextField tfTime = new JTextField(5);
		tfTime.setText("hh:mm");
		tfTime.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		pInput.add(tfTime);
		lBlank.setPreferredSize(new Dimension(280,10));
		pInput.add(lBlank);
		lBlank.setPreferredSize(new Dimension(350,5));
		pInput.add(lBlank);
		
		JButton bSearch = new JButton("Search");
		bSearch.setPreferredSize(new Dimension(90,25));
		bSearch.setBackground(Color.LIGHT_GRAY);
		pInput.add(bSearch);
		JButton bClear = new JButton("Clear");
		bClear.setPreferredSize(new Dimension(90,25));
		bClear.setBackground(Color.LIGHT_GRAY);
		pInput.add(bClear);
		
		//Component for the output
		pOutput.setPreferredSize(new Dimension(440,0));
		pOutput.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel lTitle2 = new JLabel("Connection");
		lTitle2.setPreferredSize(new Dimension(375,30));
		lTitle2.setAlignmentX(JLabel.LEFT_ALIGNMENT);
		lTitle2.setFont(new Font("arial",1,16));
		pOutput.add(lTitle2);
		
		//Adds input mask panel and result panel together as one
		pWest.setPreferredSize(new Dimension(440,0));
		pWest.setAlignmentX(Component.LEFT_ALIGNMENT);
		pWest.setAlignmentY(Component.TOP_ALIGNMENT);
		pWest.add(pInput);
		pWest.add(pOutput);
		
		//Component for the CENTER frame
		JTextField tfMap = new JTextField("MAP");

		//assigns the components to the certain frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);
		frame.getContentPane().add(BorderLayout.NORTH, lBanner).setBackground(Color.WHITE);
		frame.getContentPane().add(BorderLayout.WEST, pWest).setBackground(Color.WHITE);
		frame.getContentPane().add(BorderLayout.CENTER, tfMap).setBackground(Color.WHITE);
		frame.setSize(1000,750);
		frame.setVisible(true);	
	}
}
