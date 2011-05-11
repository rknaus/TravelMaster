package ch.netgeek.travelmaster.gui;

import javax.swing.*;

import java.awt.*;

public class GUI {
	
	public static void main(String[] args){
		GUI gui = new GUI();
		gui.setFrames();
	}
	
	public void setFrames(){
		
		JFrame frame = new JFrame();
		JPanel pInput = new JPanel();
		JPanel pOutput = new JPanel();
		JButton bSearch = new JButton();
		JButton bClear = new JButton();
		
		JLabel lStart = new JLabel("Start:");
			lStart.setPreferredSize(new Dimension(60,10));
		JTextField tfStart = new JTextField(30);
			tfStart.setText("Abfahrtsort");
		JLabel lEnd = new JLabel("Ziel:");
			lEnd.setPreferredSize(new Dimension(60,10));
		JTextField tfEnd = new JTextField(30);
			tfEnd.setText("Zielort");
		JLabel lTime = new JLabel("Abfahrt:");
			lTime.setPreferredSize(new Dimension(60,10));
		JTextField tfTime = new JTextField(5);
			tfTime.setText("10:32");
		JTextField tfMap = new JTextField("MAP");
		
		pInput.setPreferredSize(new Dimension(440,0));
		pInput.setAlignmentX(Component.LEFT_ALIGNMENT);
		pInput.add(lStart);
		pInput.add(tfStart);
		pInput.add(lEnd);
		pInput.add(tfEnd);
		pInput.add(lTime);
		pInput.add(tfTime);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(BorderLayout.WEST, pInput).setBackground(Color.WHITE);
		frame.getContentPane().add(BorderLayout.CENTER, tfMap).setBackground(Color.WHITE);

		frame.setSize(1000,750);
		frame.setVisible(true);
		
		
	}
	

}
