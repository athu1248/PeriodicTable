package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import model.Element;

/**
 * Class to find element details based on the selected element or the search key
 * In case of selected element, it only returns one result
 * In case of search key, it could return multiple results
 * @version 1.0
 * @author Atharva
 *
 */

public class ElementFinder {

	// static ArrayList<String> myList = new ArrayList<String>();

	/**
	 * Returns the information of element clicked on the periodic table page
	 * This method always returns one result 
	 * @param symbol the symbol of element clicked
	 * @return one element record
	 */
	public static Element getElement(String symbol) {
		Element element = new Element();
		// "/Applications/PeriodicTable.app/Contents/Resources/elementData.csv"
		try {
			File file1 = new File("elementData.csv");
			File file2 = new File("elementEConf.txt");
			File file3 = new File("elementFact.txt");
			Scanner scan1 = new Scanner(file1);
			Scanner scan2 = new Scanner(file2);
			Scanner scan3 = new Scanner(file3);
			
			String[] dataParts;
			String conf;
			String fact;

			while (scan1.hasNext() && scan2.hasNext() && scan3.hasNext()) {

				dataParts = scan1.nextLine().split(", ");
				conf = scan2.nextLine();
				fact = scan3.nextLine();

				if (dataParts[1].equalsIgnoreCase(symbol)) {
					element.setName(dataParts[0]);
					element.setSymbol(dataParts[1]);
					element.setAtmNo(Integer.parseInt(dataParts[2]));
					element.setMassNo(Integer.parseInt(dataParts[3]));
					element.seteConfig(conf);
					element.setFact(fact);
					break;
				}

			}
			scan1.close();
			scan2.close();
			scan3.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return element;

	}

	/**
	 * Returns list of elements based on the specified search key. Specific to only the search function in the app.
	 * This method could multiple results.
	 * @param searchKey the key entered by the user which could be an element's name, symbol, atomic number or mass number
	 * @return list of elements based on the specified search key
	 */
	public static ArrayList<Element> getSearchInfo(String searchKey) {
		
		ArrayList<Element> elementList = new ArrayList<Element>();

		try {
			File file1 = new File("elementData.csv");
			Scanner scan = new Scanner(file1);

			while (scan.hasNext()) {

				Element element = new Element();

				String record = scan.nextLine();
				String[] dataParts = record.split(", ");
				
				for (int i=0; i<4; i++) {
					if (dataParts[i].toUpperCase().contains(searchKey.toUpperCase())) {
						element.setName(dataParts[0]);
						element.setSymbol(dataParts[1]);
						element.setAtmNo(Integer.parseInt(dataParts[2]));
						element.setMassNo(Integer.parseInt(dataParts[3]));
						elementList.add(element);
						// To make sure that multiple instances of same element doesn't get added.
						// Thus once an instance of a particular element is added. We break from that element's for loop
						break;
					}
				}

			}

			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return elementList;

	}

}