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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.Controller;
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
	
	private static ArrayList<String> symbolList = new ArrayList<String>();
	private static ArrayList<String> atmNumList = new ArrayList<String>();

	
	/**
	 * Create a new home page containing the periodic table.
	 */
	public void newHomePage() {
		
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
				if (key.contentEquals("")) {
					matchList.clear();
					comboBox.removeAllItems();
				} else {
					matchList.clear();
					matchList = Controller.getSearchElements(key);
					comboBox.removeAllItems();
					comboBox.addItem("Name"+ String.join("", Collections.nCopies(9, " ")) + 
							"Symbol  " + "  A.No.  " + "  M.No.");
					for (Element element : matchList) {
						comboBox.addItem(element.getName() + String.join("", Collections.nCopies((15 - element.getName().length()), " ")) + 
								element.getSymbol() + String.join("", Collections.nCopies((9 - element.getSymbol().length()), " ")) + 
								element.getAtmNo() + String.join("", Collections.nCopies((9 - Integer.toString(element.getAtmNo()).length()), " ")) + 
								element.getMassNo());
					}
				}
			}
		
		});

		//Combo Box - Search Results
		gbc.gridx = 2;
		headingPanel.add(comboBox);
		comboBox.setPreferredSize(new Dimension(300, 30));
		comboBox.setFont(new Font("Courier", Font.PLAIN, 12));
		comboBox.setEditable(false);
		comboBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				int a = comboBox.getSelectedIndex();
				
				if (e.getStateChange()==ItemEvent.SELECTED) {
					if (a != 0) {
						String click_string = (String) comboBox.getSelectedItem();
						String[] click_parts = click_string.split("   ");
						Element click_element = Controller.getElement(click_parts[1]);
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
				addBtn(bodyPanel, j); //  Adding H and He
				j++;
			} else {
				bodyPanel.add(new JLabel(""));
			}

		}

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
				addBtn(bodyPanel, j);
				j++;
			}

		}
		
		// Third period
		for (int i = 0; i < 18; i++) {
			if (num.contains(i)) {
				bodyPanel.add(new JLabel(""));
			} else {
				addBtn(bodyPanel, j);
				j++;
			}

		}
		
		// Fourth, Fifth period
		for (int i = 0; i < 36; i++) {
			addBtn(bodyPanel, j);
			j++;

		}
		
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
					addBtn(bodyPanel, k);
					k++;
					// Now k becomes the local incrementing variable for element number
				} else {
					addBtn(bodyPanel, j);
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
					addBtn(bodyPanel, l);
					l++;
					// Now l becomes the local incrementing variable for element number
				} else {
					addBtn(bodyPanel, k);
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
			addBtn(bodyPanel, j);
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
			addBtn(bodyPanel, k);
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
	 * Method to add a button of an element for the periodic table.
	 * @param pnl Panel to which button is to be added.
	 * @param atmNo The atomic number of the element to be added on the button.
	 */
	private void addBtn(JPanel pnl, int atmNo) {
		JButton btn = new JButton("<html><p align = 'center'>"+symbolList.get(atmNo-1)+"<br>"+atmNumList.get(atmNo-1)+"</p></html>");
		pnl.add(btn);
		Controller.getElementColor(btn, atmNo);
		btn.setActionCommand(symbolList.get(atmNo-1));
		btn.addActionListener(new HomeView());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		String action_symbol = e.getActionCommand();
		Element element_Result = Controller.getElement(action_symbol);
		ElementView.elementPage(element_Result);

	}

}