import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 * This class creates the top level JPanel onto which the other sub-panels are
 * added. Since it's the top level panel, the book button and load button
 * listeners are also created here.
 *
 * @author Joshua McKerracher
 */

public class ReaderOfBooksPanel extends JPanel {
	private LibraryPanel libraryPanel;
	private ReaderPanel readerPanel;
	private Library library;
	private Library libraryTwo;
	private Library libraryThree;
	private ArrayList<JButton> bookButtonList;
	private JTextField newLoadField;
	private JButton theBookButton;
	private ArrayList<String> titleBooks;
	private String bookText;
	private Border simpleBorder;

	private ReaderOfBooksPanel() {
		this.setLayout(new BorderLayout());
		simpleBorder = BorderFactory.createLineBorder(Color.GRAY);
		library = new Library();
		libraryPanel = LibraryPanel.getInstance();
		readerPanel = ReaderPanel.getInstance();
		this.setBorder(simpleBorder);
		this.add(libraryPanel, BorderLayout.WEST);
		this.add(readerPanel, BorderLayout.CENTER);
		titleBooks = new ArrayList();

		// get load buttons from library panel
		libraryPanel.getLoadButton();
		libraryPanel.getLoadButton().addActionListener(new LoadButtonListener());

		// get loadfield from library panel
		newLoadField = libraryPanel.getLoadField();

		// Creates an array of the created book buttons that can be called from the book
		// button listener
		bookButtonList = new ArrayList();
		bookText = new String();

		libraryThree = new Library();
	}

	private static ReaderOfBooksPanel theInstance = new ReaderOfBooksPanel();

	/*
	 * This is a method to create an instance of the reader of books panel.
	 */
	public static ReaderOfBooksPanel getInstance() {
		return theInstance;
	}

	// Load button listener that uses loadlibraryfromcsv to load the library
	private class LoadButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			libraryTwo = new Library();

			String loadStringfile = newLoadField.getText();
			libraryTwo.loadLibraryFromCSV(loadStringfile);

			// for loop that creates buttons for the books
			for (int i = 0; i < libraryTwo.size(); i++) {
				BookButton b = new BookButton();
				theBookButton = b.makeBookButton(libraryTwo.getBook(i));

				// add BookButtonLister to each book button
				theBookButton.addActionListener(new BookButtonListener());

				// add to an array of book buttons so they can be access in the book button
				// listener.
				bookButtonList.add(theBookButton);
				libraryThree.addBook(libraryTwo.getBook(i));
				libraryPanel.bookListPanel.add(theBookButton);
			}
			libraryPanel.revalidate();
			libraryPanel.bookListPanel.revalidate();
		}
	}

	// Book button listener that uses the bookButtonList and library three
	// ArrayLists.
	private class BookButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < bookButtonList.size(); i++) {
				if (e.getSource() == bookButtonList.get(i)) {
					bookText = libraryThree.getBook(i).getText();
					readerPanel.setBookText(bookText);
					readerPanel.setTitleInfo(libraryThree.getBook(i).getTitle());
					readerPanel.setAuthorInfo(libraryThree.getBook(i).getAuthor());
					readerPanel.revalidate();
					readerPanel.repaint();
				}
			}
		}
	}

}