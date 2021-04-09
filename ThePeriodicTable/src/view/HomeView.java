package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ElementFinder;
import model.Element;

public class HomeView implements ActionListener {

	private static JFrame frame = new JFrame();
	private static JPanel headingPanel = new JPanel();
	private static JPanel bodyPanel = new JPanel();
	private static GridLayout grid = new GridLayout(0, 18, 0, 0);

	private static JLabel heading = new JLabel("The Periodic Table                 ");

	private static JComboBox<String> comboBox = new JComboBox<String>();
	private static JTextField txtSearch = new JTextField();
	
	private static ArrayList<Element> matchList = new ArrayList<Element>();
	
	
	private static List<Integer> cyan = new ArrayList<Integer>();
	private static List<Integer> red = new ArrayList<Integer>();
	private static List<Integer> orange = new ArrayList<Integer>();
	private static List<Integer> yellow = new ArrayList<Integer>();
	private static List<Integer> green = new ArrayList<Integer>();
	private static List<Integer> blue = new ArrayList<Integer>();
	private static List<Integer> pink = new ArrayList<Integer>();
	private static List<Integer> magenta = new ArrayList<Integer>();
	private static List<Integer> lgray = new ArrayList<Integer>();
	
	/**
	 * 
	 */
	public void newHomePage() {
		
		Collections.addAll(cyan, 1,6,7,8,15,16,34);
		Collections.addAll(red, 3,11,19,37,55,87);
		Collections.addAll(orange, 4,12,20,38,56,88);
		Collections.addAll(yellow, 21,22,23,24,25,26,27,28,29,30,
				39,40,41,42,43,44,45,46,47,48,72,73,74,75,76,77,78,79,80,
				104,105,106,107,108,109,110,111,112);
		Collections.addAll(green, 13,31,49,50,81,82,83,113,114,115,116);
		Collections.addAll(blue, 5,14,32,33,51,52,84);
		Collections.addAll(pink, 9,17,35,53,85,117);
		Collections.addAll(magenta, 2,10,18,36,54,86,118);
		Collections.addAll(lgray, 57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,
				89,90,91,92,93,94,95,96,97,98,99,100,101,102,103);

		ArrayList<String> symbolList = new ArrayList<String>();
		ArrayList<String> atmNumList = new ArrayList<String>();
		File file = new File("elementData.csv");
		
		// Adding the symbols and atomic numbers to their corresponding lists
		try {
			Scanner scan = new Scanner(file);
			while (scan.hasNext()) {
				String record = scan.nextLine();
				String[] parts = record.split(", ");
				symbolList.add(parts[1]);
				atmNumList.add(parts[2]);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// Heading Panel!!!!!
		headingPanel.setBackground(Color.LIGHT_GRAY);
		headingPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		Font font1 = new Font("Consolas", Font.ITALIC, 25);
		gbc.gridx = 0;
		heading.setHorizontalAlignment(SwingConstants.LEFT);
		headingPanel.add(heading);
		heading.setFont(font1);

		// Search Text Field
		gbc.gridx = 1;
		headingPanel.add(txtSearch);
		txtSearch.setPreferredSize(new Dimension(150, 30));
		txtSearch.setTransferHandler(null);
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			public void insertUpdate(DocumentEvent e) {
				changeSuggestions(txtSearch.getText());
			}

			public void removeUpdate(DocumentEvent e) {
				changeSuggestions(txtSearch.getText());
			}

			public void changedUpdate(DocumentEvent e) {
				changeSuggestions(txtSearch.getText());
			}
			
			public void changeSuggestions(String key) {
				matchList.clear();
				matchList = ElementFinder.getSearchInfo(key);
				comboBox.removeAllItems();
				comboBox.addItem("Name  " + "  Symbol  " + "  A.No.  " + "  M.No.");
				for (Element element : matchList) {
					comboBox.addItem(element.getName() +"   "+ element.getSymbol() +"   "+ element.getAtmNo() +"   "+ element.getMassNo());
				}
			}
		
		});

		//Combo Box - Search Results
		gbc.gridx = 2;
		headingPanel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(300, 30));
		comboBox.setEditable(false);
		comboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				int a = comboBox.getSelectedIndex();
				
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (a != 0) {
						String click_string = (String) comboBox.getSelectedItem();
						String[] click_parts = click_string.split("   ");
						Element click_element = ElementFinder.getElement(click_parts[1]);
						ElementView.elementPage(click_element);
					}
				}
			}
		});
		
		// Mid-Panel!!!!!
		bodyPanel.setBackground(Color.DARK_GRAY);
		bodyPanel.setLayout(grid);

		// Extra top spacing
		// A total of 18 columns
		for (int i = 0; i < 18; i++) {
			bodyPanel.add(new JLabel(""));
		}

		int j = 1; // local var j made for the element number. Increments by 1 when a element is added to the table.

		// First period
		for (int i = 0; i < 18; i++) {
			if (i == 0 || i == 17) {
				addBtn(bodyPanel, symbolList, j, atmNumList); //  Adding H and He
				j++;
			} else {
				bodyPanel.add(new JLabel(""));
			}

		}
		// System.out.println(j); = 3

		// Second period
		List<Integer> num = new ArrayList<Integer>(); 
		for (int i = 2; i < 12; i++) {
			num.add(i);
		}
		// num created for the space between s block and p block. It contains the column number of the empty spaces.
		for (int i = 0; i < 18; i++) {
			if (num.contains(i)) {
				bodyPanel.add(new JLabel(""));
			} else {
				addBtn(bodyPanel, symbolList, j, atmNumList);
				j++;
			}

		}
		// System.out.println(j); = 11
		
		// Third period
		for (int i = 0; i < 18; i++) {
			if (num.contains(i)) {
				bodyPanel.add(new JLabel(""));
			} else {
				addBtn(bodyPanel, symbolList, j, atmNumList);
				j++;
			}

		}
		// System.out.println(j); = 19
		
		// Fourth, Fifth period
		for (int i = 0; i < 36; i++) {
			addBtn(bodyPanel, symbolList, j, atmNumList);
			j++;

		}
		// System.out.println(j); = 55
		
		// Sixth period
		int k = 72; // var k for skipping the lanthanoids and continuing with the sixth period
		for (int i = 0; i < 18; i++) {
			if (i == 2) { // To add a label in the sixth period for separation in third column
				JLabel lbl1 = new JLabel("57-71");
				bodyPanel.add(lbl1);
				lbl1.setOpaque(true);
				lbl1.setBackground(Color.LIGHT_GRAY);
				lbl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			} else {
				if (j > 56) { // Once j becomes greater than 56, we use k to continue with the sixth period
					// It does not continue forever as the for loop is set to run only for 18 times
					addBtn(bodyPanel, symbolList, k, atmNumList);
					k++;
					// Now k becomes the local incrementing variable for element number
				} else {
					addBtn(bodyPanel, symbolList, j, atmNumList);
					j++; // value for j stops incrementing to use again for lanthanoids
				}

			}
		}
		// System.out.println(k); = 57
		// System.out.println(l); = 87
		
		// Seventh period
		int l = 104; // var l for skipping the actinoids and continuing with the seventh period
		for (int i = 0; i < 18; i++) {
			if (i == 2) { // To add a label in the seventh period for separation in third column
				JLabel lbl1 = new JLabel("89-103");
				bodyPanel.add(lbl1);
				lbl1.setOpaque(true);
				lbl1.setBackground(Color.LIGHT_GRAY);
				lbl1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			} else {
				if (k > 88) { // Once k becomes greater than 88, we use l to continue with the sixth period
					// It does not continue forever as the for loop is set to run only for 18 times
					addBtn(bodyPanel, symbolList, l, atmNumList);
					l++;
					// Now l becomes the local incrementing variable for element number
				} else {
					addBtn(bodyPanel, symbolList, k, atmNumList);
					k++; // value for k stops incrementing to use again for actinoids
				}
			}
		}
		// System.out.println(k); = 89
		// System.out.println(l); = 119
		
		//Dividing spacing
		for (int i = 0; i < 18; i++) {
			bodyPanel.add(new JLabel(""));
		}
		
		// Lactinoids
		for (int i = 0; i < 1; i++) {
			bodyPanel.add(new JLabel(""));
		}
		for (int i = 0; i < 15; i++) {
			addBtn(bodyPanel, symbolList, j, atmNumList);
			j++;
		}
		for (int i = 0; i < 2; i++) {
			bodyPanel.add(new JLabel(""));
		}
		
		// Actinoids
		for (int i = 0; i < 1; i++) {
			bodyPanel.add(new JLabel(""));
		}
		for (int i = 0; i < 15; i++) {
			addBtn(bodyPanel, symbolList, k, atmNumList);
			k++;
		}
		for (int i = 0; i < 2; i++) {
			bodyPanel.add(new JLabel(""));
		}
		
		// Extra bottom spacing
		for (int i = 0; i < 18; i++) {
			bodyPanel.add(new JLabel(""));
		}
		

		frame.getContentPane().add(headingPanel, BorderLayout.NORTH);
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

	/**
	 * 
	 * @param a
	 * @param i
	 */
	protected void color(JComponent a, int i) {

		if (cyan.contains(i)) {
			a.setOpaque(true);
			a.setBackground(Color.CYAN);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (red.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.RED);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		}else if (orange.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.ORANGE);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (yellow.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.YELLOW);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (green.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.GREEN);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (blue.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.BLUE);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (pink.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.PINK);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (magenta.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.MAGENTA);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}else if (lgray.contains(i)){
			a.setOpaque(true);
			a.setBackground(Color.LIGHT_GRAY);
			a.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		
	}

	/**
	 * 
	 * @param pnl
	 * @param symbol
	 * @param j
	 * @param atmNo
	 */
	private void addBtn(JPanel pnl, ArrayList<String> symbol, int j, ArrayList<String> atmNo) {
		JButton a = new JButton("<html><p align = 'center'>"+symbol.get(j-1)+"<br>"+atmNo.get(j-1)+"</p></html>");
		pnl.add(a);
		color(a, j);
		a.setActionCommand(symbol.get(j-1));
		a.addActionListener(new HomeView());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String action_symbol = e.getActionCommand();
		Element element_Result = ElementFinder.getElement(action_symbol);
		ElementView.elementPage(element_Result);

	}

}