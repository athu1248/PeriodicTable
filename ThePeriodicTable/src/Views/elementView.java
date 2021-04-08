package Views;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class elementView {

	static JFrame frame = new JFrame();
	static JPanel panel1 = new JPanel();

	static JLabel lbl1 = new JLabel("Name:");
	static JLabel lbl2 = new JLabel("Symbol:");
	static JLabel lbl3 = new JLabel("Atomic Number:");
	static JLabel lbl4 = new JLabel("Mass Number:");
	static JLabel lbl5 = new JLabel("Electronic Configuration:");
	static JLabel lbl6 = new JLabel("Fun fact:");

	static JLabel lblElement = new JLabel("", SwingConstants.CENTER);
	static JLabel lblName = new JLabel();
	static JLabel lblSymbol = new JLabel();
	static JLabel lblatmNo = new JLabel();
	static JLabel lblmassNo = new JLabel();
	static JLabel lblConfig = new JLabel();
	static JLabel lblFact = new JLabel();
	

	static GridBagLayout grid1 = new GridBagLayout();
	static GridBagConstraints gbc = new GridBagConstraints();

	/**
	 * 
	 * @param name
	 * @param symbol
	 * @param atmNo
	 * @param massNo
	 * @param config
	 * @param fact
	 */
	public static void elementPage(String name, String symbol, String atmNo, String massNo, String config, String fact) {

		// PANEL 1
		panel1.setLayout(grid1);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 6;
		gbc.fill = GridBagConstraints.VERTICAL;
		lblElement.setPreferredSize(new Dimension(400, 375));
		Font font1 = new Font("Consolas", Font.BOLD, 100);
		lblElement.setText(symbol);
		lblElement.setFont(font1);
		homeView h = new homeView();
		h.color(lblElement, Integer.valueOf(atmNo));
		panel1.add(lblElement, gbc);

		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.fill = GridBagConstraints.NONE;
		
		gbc.ipadx = 40;

		gbc.gridx = 1;
		gbc.gridy = 0;
		panel1.add(lbl1, gbc);
		lblName.setText(name);
		lblName.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 0;
		panel1.add(lblName, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel1.add(lbl2, gbc);
		lblSymbol.setText(symbol);
		lblSymbol.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 1;
		panel1.add(lblSymbol, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		panel1.add(lbl3, gbc);
		lblatmNo.setText(atmNo);
		lblatmNo.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 2;
		panel1.add(lblatmNo, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel1.add(lbl4, gbc);
		lblmassNo.setText(massNo);
		lblmassNo.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 3;
		panel1.add(lblmassNo, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		panel1.add(lbl5, gbc);
		lblConfig.setText(config);
		lblConfig.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 4;
		panel1.add(lblConfig, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel1.add(lbl6, gbc);
		if (fact.contentEquals("<html></html>")) {
			lblFact.setText("N/A");
		}else {
			lblFact.setText(fact);
		}
		lblFact.setPreferredSize(new Dimension(200, 75));
		gbc.gridx = 2;
		gbc.gridy = 5;
		panel1.add(lblFact, gbc);

		frame.getContentPane().add(panel1, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}

}