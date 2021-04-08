package controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to find element details based on the selected element or the search key
 * In case of selected element, it only returns one result
 * In case of search key, it could return multiple results
 * @version 1.0
 * @author Atharva
 *
 */

public class ElementFinder {

	static ArrayList<String> myList = new ArrayList<String>();

	/**
	 * Returns the information of element clicked on the periodic table page
	 * This method always returns one result 
	 * @param symbol the symbol of element clicked
	 * @return one element record
	 */
	public static String[] getElementInfo(String symbol) {
		// "/Applications/PeriodicTable.app/Contents/Resources/elementData.csv"
		File file1 = new File("elementData.csv");
		File file2 = new File("elementConf.csv");
		File file3 = new File("elementFact.csv");
		String[] result = new String[6];
		try {
			Scanner scan1 = new Scanner(file1);
			Scanner scan2 = new Scanner(file2);
			Scanner scan3 = new Scanner(file3);

			while (scan1.hasNext() && scan2.hasNext() && scan3.hasNext()) {

				String data = scan1.nextLine();
				String conf = scan2.nextLine();
				String fact = scan3.nextLine();
				String record = data + ", " + conf + ", " + fact;
				String[] parts = record.split(", ");

				if (parts[1].equalsIgnoreCase(symbol)) {
					result[0] = parts[0];
					result[1] = parts[1];
					result[2] = parts[2];
					result[3] = parts[3];
					result[4] = conf;
					result[5] = fact;
					break;
				}

			}

			scan1.close();
			scan2.close();
			scan3.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Returns list of elements based on the specified search key. Specific to only the search function in the app.
	 * This method could multiple results.
	 * @param searchKey the key entered by the user which could be an element's name, symbol, atomic number or mass number
	 * @return list of elements based on the specified search key
	 */
	public static ArrayList<String> getSearchInfo(String searchKey) {
		
		myList.clear();

		File file1 = new File("elementData.csv");

		try {
			Scanner scan = new Scanner(file1);

			Boolean bool1 = true;

			while (scan.hasNext() && bool1) {

				String record = scan.nextLine();
				String[] parts = record.split(", ");
				
				for (int i=0; i<4; i++) {
					if (parts[i].toUpperCase().contains(searchKey.toUpperCase())) {

						myList.add(String.valueOf(record));
						break;

					}
				}

			}

			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return myList;

	}

}