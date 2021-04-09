package controller;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import model.Element;

public class Controller {

	private static Integer[] cyanInt = new Integer[]{1,6,7,8,15,16,34};
	private static Integer[] redInt = new Integer[]{3,11,19,37,55,87};
	private static Integer[] orangeInt = new Integer[]{4,12,20,38,56,88};
	private static Integer[] yellowInt = new Integer[]{21,22,23,24,25,26,27,28,29,30,
			39,40,41,42,43,44,45,46,47,48,72,73,74,75,76,77,78,79,80,
			104,105,106,107,108,109,110,111,112};
	private static Integer[] greenInt = new Integer[]{13,31,49,50,81,82,83,113,114,115,116};
	private static Integer[] blueInt = new Integer[]{5,14,32,33,51,52,84};
	private static Integer[] pinkInt = new Integer[]{9,17,35,53,85,117};
	private static Integer[] magentaInt = new Integer[]{2,10,18,36,54,86,118};
	private static Integer[] lgrayInt = new Integer[]{57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,
			89,90,91,92,93,94,95,96,97,98,99,100,101,102,103};
	
	private static List<Integer> cyan = new ArrayList<>(Arrays.asList(cyanInt));
	private static List<Integer> red = new ArrayList<>(Arrays.asList(redInt));
	private static List<Integer> orange = new ArrayList<>(Arrays.asList(orangeInt));
	private static List<Integer> yellow = new ArrayList<>(Arrays.asList(yellowInt));
	private static List<Integer> green = new ArrayList<>(Arrays.asList(greenInt));
	private static List<Integer> blue = new ArrayList<>(Arrays.asList(blueInt));
	private static List<Integer> pink = new ArrayList<>(Arrays.asList(pinkInt));
	private static List<Integer> magenta = new ArrayList<>(Arrays.asList(magentaInt));
	private static List<Integer> lgray = new ArrayList<>(Arrays.asList(lgrayInt));

	/**
	 * Returns the information of element clicked on the periodic table page
	 * This method always returns one result 
	 * @param symbol the symbol of element clicked
	 * @return one element record
	 */
	public static Element getElement(String symbol) {
		Element element = new Element();
		// "/Applications/Periodic Table.app/Contents/Resources/elementData.csv"
		// "/Applications/Periodic Table.app/Contents/Resources/elementEConf.txt"
		// "/Applications/Periodic Table.app/Contents/Resources/elementFact.txt"
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
	public static ArrayList<Element> getSearchElements(String searchKey) {
		
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
	
	/**
	 * Method to set the background color of a JComponent according to the element
	 * @param a The JComponent to add the color.
	 * @param atmNo The atomic number of the element to get the color of.
	 */
	public static void getElementColor(JComponent a, int atmNo) {

		if (cyan.contains(atmNo)) {
			a.setOpaque(true);
			a.setBackground(Color.CYAN);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (red.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.RED);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		} else if (orange.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.ORANGE);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (yellow.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.YELLOW);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (green.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.GREEN);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (blue.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.BLUE);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (pink.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.PINK);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (magenta.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.MAGENTA);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		} else if (lgray.contains(atmNo)){
			a.setOpaque(true);
			a.setBackground(Color.LIGHT_GRAY);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		
	}

}