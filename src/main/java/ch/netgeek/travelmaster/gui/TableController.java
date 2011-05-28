package ch.netgeek.travelmaster.gui;
import ch.netgeek.travelmaster.algorithm.Stopover;

import java.util.ArrayList;

public class TableController {
	
    private ArrayList<Stopover> connectionResult;
    private GUI gui;
    private TableModel tableModel;
    
    public TableController(ArrayList<Stopover> connectionResult){
    	this.connectionResult = connectionResult;
    	tableModel = new TableModel(connectionResult);
//    	gui = new GUI(tableModel);
    }
    
    public void getView(){
    	//getView Code
    }
}
