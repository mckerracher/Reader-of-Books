import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * Driver class that uses a JFrame. Adds the reader of books panel as a JPanel.
 *
 * @author Joshua McKerracher
 */

public class ReaderOfBooks {
	public static void main(String[] args) {
		// Setting up JFrame
		JFrame frame = new JFrame("Reader Of Books");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		// 1000 x 700
		frame.setPreferredSize(new Dimension(1000, 700));
		// frame.add(new ReaderOfBooksPanel(),BorderLayout.CENTER);
		frame.getContentPane().add(ReaderOfBooksPanel.getInstance());

		// Make visible
		frame.pack();
		frame.setVisible(true);
	}
}