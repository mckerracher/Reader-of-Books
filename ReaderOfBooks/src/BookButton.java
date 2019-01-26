import java.awt.Dimension;

import javax.swing.JButton;

/*
* Class that allows the user to create create a book buttons.
 *
 * @author Joshua McKerracher
*
 */

public class BookButton extends JButton {
	private JButton bookButton = new JButton();

	/*
	 * Creates JButtons with the book title as the text of the button. Also sets the
	 * tool tip text to the book information, pulled from the book class to string
	 * method.
	 *
	 * * @author Joshua McKerracher
	 */
	public JButton makeBookButton(Book book)

	{
		int charCheck = 19;
		String truncatedTitle;

		// check if book title is > 20 characters & set text
		if (book.getTitle().length() > charCheck) {
			truncatedTitle = book.getTitle().substring(0, 19);
			bookButton.setText(truncatedTitle);
		}

		// check if book title is <= 20 characters & set text
		if (book.getTitle().length() <= charCheck) {
			bookButton.setText(book.getTitle());
		}

		// set size formatting
		bookButton.setMinimumSize(new Dimension(250, 35));
		bookButton.setMaximumSize(new Dimension(250, 35));

		// set tool tip text
		bookButton.setToolTipText(book.toString());

		// return
		return bookButton;

	}
}