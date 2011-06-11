package ch.netgeek.travelmaster.gui;

import ch.netgeek.travelmaster.algorithm.Stopover;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

/**
 * This class requests data from the arraylist.
 * 
 * @author 	        Ruben Knaus, Dieu P. Van
 * @version         1.0
 *
 */
public class TableModel extends AbstractTableModel {

	/**
	 * serialVersionUID is required by the AbstractTableModel class
	 */
	private static final long serialVersionUID = 6524119601232296864L;

	// variable declaration
	private ArrayList<Stopover> stopoverList;
	private SimpleDateFormat formatter;

	/**
	 * The constructor of the TableModel class requires data to be returned by
	 * its functions.
	 * 
	 * @param connectionResult      list of connection to be stored in the <br>
	 * object instance
	 */
	public TableModel(ArrayList<Stopover> stopoverList) {
		this.stopoverList = stopoverList;
		formatter = new SimpleDateFormat("HH:mm");
	}

	/**
	 * Returns the number of columns and implements the needed columns.
	 * 
	 * @return              the number of columns
	 */
	@Override
	public int getColumnCount() {
		return 6;
	}

	/**
	 * Returns the number of rows.
	 * 
	 * @return		the number of rows
	 */
	@Override
	public int getRowCount() {
		if (stopoverList.isEmpty()) {
			return 10;
		}     
		return stopoverList.size();
	}

	/**
	 * The getValue method returns the data of the fields of the JTable.
	 * 
	 * @param rowIndex      specifies the row number to get data from
	 * @param columnIndex   specifies the column number to get data from
	 * @return              Object-Array with two dimensions
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		/* 
		 * If the stopover list has no entries, give back a message which says 
		 * that there is no data.
		 */
		if (stopoverList.isEmpty()) {
			return " ";
		}

		/*
		 * Depending on the rowIndex and the columnIndex, the correct data is
		 * given out
		 */

		// Source station column
		if (columnIndex == 0) {                 
			return " " + stopoverList.get(rowIndex).getSource().getName();

			// Destination column
		} else if (columnIndex == 1) {
			return " " + stopoverList.get(rowIndex).getDestinatio().getName();

			// line column
		} else if (columnIndex == 2) {          
			return stopoverList.get(rowIndex).getLine().getNumber();

			// departure column
		} else if (columnIndex == 3) {          
			Calendar departureTime = stopoverList.get(rowIndex).getDepartureTime();
			String time = " ";
			if (departureTime != null) {
				time = time + formatter.format(departureTime.getTime());
			}
			return time;

			// arrival column
		} else if (columnIndex == 4) {  
			Calendar arrivalTime = stopoverList.get(rowIndex).getArrivalTime();
			String time = " ";
			if (arrivalTime != null) {
				time = time + formatter.format(arrivalTime.getTime());
			}
			return time;

			// duration column
		} else if (columnIndex == 5) {          
			return stopoverList.get(rowIndex).getTravelDuration();
		}

		/* returns null if the columnIndex is too small or too big */
		return null;
	}
}

