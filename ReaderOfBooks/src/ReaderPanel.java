import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Creates the Reader panel, which contains further sub-panels (JPanels).
 *
 * @author Joshua McKerracher
 */

public class ReaderPanel extends JPanel {

	private Border simpleBorder;
	private JPanel infoPanel;
	private JPanel contentPanel;
	private JPanel navigationPanel;

	private JLabel infoTitle;
	private JLabel infoAuthor;
	private JLabel infoPage;

	private JButton upButton;
	private JButton downButton;

	private JTextArea bookText;
	private JScrollPane contentScroll;
	private String placeholder;

	private int pageLength;
	private int bookLength;
	private int currentPage;
	private int currentPosition;
	private int maxPosition;

	// Creates reader panel.
	private ReaderPanel() {
		simpleBorder = BorderFactory.createLineBorder(Color.GRAY);
		this.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Reader"));
		this.setLayout(new BorderLayout());

		// start of info panel:
		Box iBox;
		iBox = Box.createHorizontalBox();

		placeholder = new String("nothing right now");

		infoPanel = new JPanel();

		infoPanel.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Information"));
		infoPanel.setLayout(new GridLayout(0, 1));

		infoTitle = new JLabel();
		infoTitle.setText("Title: " + placeholder);
		iBox.add(infoTitle);
		iBox.add(Box.createGlue());

		infoAuthor = new JLabel();
		infoAuthor.setText("By: " + placeholder);
		iBox.add(infoAuthor);
		iBox.add(Box.createGlue());

		infoPage = new JLabel();
		infoPage.setText("Page: " + placeholder);
		iBox.add(infoPage);

		infoPanel.add(iBox);
		this.add(infoPanel, BorderLayout.NORTH);

		// start of content panel (ReaderPanel -> Content Panel):
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Content"));
		contentPanel.setLayout(new BorderLayout());

		// Create JScrollPane and add it to ContentPanel
		contentScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// create book text area
		bookText = new JTextArea();
		bookText.setEditable(false);
		bookText.setText("Nothing right now.");

		// add text and listener to scroll pane
		contentScroll.setViewportView(bookText);
		contentScroll.getVerticalScrollBar().addAdjustmentListener(new scrollListener());
		contentScroll.getHorizontalScrollBar().addAdjustmentListener(new scrollListener());

		// add scroll pane to content panel
		contentPanel.add(contentScroll, BorderLayout.CENTER);

		// add content panel to reader panel
		this.add(contentPanel, BorderLayout.CENTER);

		// start of navigation panel:
		navigationPanel = new JPanel();
		this.add(navigationPanel, BorderLayout.SOUTH);
		navigationPanel.setBorder(BorderFactory.createTitledBorder(simpleBorder, "Navigation"));

		upButton = new JButton();
		upButton.setText("Page Up");
		upButton.addActionListener(new pageUpListener());
		navigationPanel.add(upButton, BorderLayout.WEST);

		downButton = new JButton();
		downButton.setText("Page Down");
		downButton.addActionListener(new pageDownListener());
		navigationPanel.add(downButton, BorderLayout.EAST);

		// revalidate and repaint:
		this.revalidate();
		this.repaint();
	}

	private static ReaderPanel theInstance = new ReaderPanel();

	/*
	 * Allows the user to get an instance of the reader panel while keeping it
	 * private.
	 */
	public static ReaderPanel getInstance() {
		return theInstance;
	}

	/*
	 * Method to set title info to information panel from another class.
	 */
	public void setTitleInfo(String text) {
		infoTitle.setText("Title: " + text);
	}

	/*
	 * Method to set author info to information panel from another class.
	 */
	public void setAuthorInfo(String text) {
		infoAuthor.setText("By: " + text);
	}

	/*
	 * Method to set text in bookText JTextArea from another class.
	 */
	public void setBookText(String text) {
		bookText.setText(text);

		pageLength = contentScroll.getVerticalScrollBar().getBlockIncrement(-1);

		infoPage.setText("Page: " + Integer.toString(currentPage) + "/" + bookLength);

		contentScroll.getVerticalScrollBar().setValue(1);

		bookText.revalidate();
	}

	// Listeners
	private class pageUpListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == upButton) {
				// checks if the current position leaves enough room to move up one page.
				if (currentPosition > 0) {
					int OnePage = currentPosition - pageLength;
					contentScroll.getVerticalScrollBar().setValue(OnePage);
					infoPage.setText("Page: " + Integer.toString(currentPage) + "/" + bookLength);
				}

				contentPanel.revalidate();
				contentPanel.repaint();
			}
		}
	}

	private class pageDownListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// checks if the event source is the down button
			if (e.getSource() == downButton) {
				// checks if the current position leaves enough room to move down one page.
				if (currentPosition < maxPosition) {
					int downOnePage = currentPosition + pageLength;
					contentScroll.getVerticalScrollBar().setValue(downOnePage);
					infoPage.setText("Page: " + Integer.toString(currentPage) + "/" + bookLength);
				}

				contentPanel.revalidate();
				contentPanel.repaint();
			}
		}
	}

	private class scrollListener implements AdjustmentListener {
		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			currentPosition = contentScroll.getVerticalScrollBar().getValue();
			maxPosition = contentScroll.getVerticalScrollBar().getMaximum();

			pageLength = contentScroll.getVerticalScrollBar().getBlockIncrement(-1);

			bookLength = (contentScroll.getVerticalScrollBar().getMaximum() / pageLength);

			currentPage = (1 + (contentScroll.getVerticalScrollBar().getValue() / pageLength));

			infoPage.setText("Page: " + Integer.toString(currentPage) + "/" + bookLength);

			contentPanel.revalidate();
			contentPanel.repaint();

		}
	}

}