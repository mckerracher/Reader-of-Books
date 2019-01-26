import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * Creates the library panel, which contains further sub-panels (JPanels).
 *
 * @author Joshua McKerracher
 */

public class LibraryPanel extends JPanel {
	private JButton[] buttons;
	private JButton[] loadButtons;
	private JTextField loadField;
	private JButton loadButton;
	private Border simpleBorder;
	private Border lowerBorder;
	public JPanel scrollPane;
	public JPanel bookListPanel;
	private JPanel importPanel;
	private JButton theBookButton;

	private LibraryPanel() {
		buttons = new JButton[10];
		loadButtons = new JButton[1];

		// set library panel layout and border
		this.setLayout(new BorderLayout());
		simpleBorder = BorderFactory.createLineBorder(Color.GRAY);
		lowerBorder = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		this.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Library"));

		// create a booklistpanel that's added to the jscrollpane
		bookListPanel = new JPanel();
		bookListPanel.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Book List"));
		bookListPanel.setLayout(new BoxLayout(bookListPanel, BoxLayout.Y_AXIS));

		// create a jscrollpane that contains the book list panel added to LibraryPanel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setLocation(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(bookListPanel);
		this.add(scrollPane);

		// create the import panel and add it to the bottom of the library panel
		importPanel = new JPanel();
		this.add(importPanel, BorderLayout.SOUTH);
		importPanel.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Import Books"));

		// load field and load button for import books panel
		loadField = new JTextField();
		loadField.setPreferredSize(new Dimension(170, 25));
		importPanel.add(loadField);

		loadButton = new JButton();
		loadButton.setText("Load");
		importPanel.add(loadButton);
		loadButtons[0] = loadButton;
	}

	private static LibraryPanel theInstance = new LibraryPanel();

	/*
	 * This is a method to create an instance of the library panel.
	 */
	public static LibraryPanel getInstance() {
		return theInstance;
	}

	/*
	 * This is a method that allows the user to access the load field and its
	 * contents from another class.
	 */
	public JTextField getLoadField() {
		return loadField;
	}

	/*
	 * This is a method that allows the user to access the load button from another
	 * class.
	 */
	public JButton getLoadButton() {
		return loadButtons[0];
	}

}