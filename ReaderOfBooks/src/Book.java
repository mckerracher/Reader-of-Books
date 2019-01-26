import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This is a class that makes book objects and defines their attributes and
 * methods.
 *
 * @author Joshua McKerracher
 *
 */
public class Book {

	// attributes:
	private String title;
	private String author;
	private String genre;
	private String filename;

	/*
	 * Constructor for the book object that takes two strings as arguments: the book
	 * title and author.
	 *
	 */
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		genre = null;
		filename = null;
	}

	// getters and setters:

	/*
	 * This method gets the title of a book and returns it.
	 *
	 */
	public String getTitle() {
		return title;
	}

	/*
	 * This methods sets the title of a book.
	 *
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * This method gets the author of a book and returns it.
	 *
	 */
	public String getAuthor() {
		return author;
	}

	/*
	 * This methods set the author of a book.
	 *
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/*
	 * This method gets the genre of a book and returns it.
	 *
	 */
	public String getGenre() {
		return genre;
	}

	/*
	 * This method sets the genre of a book.
	 *
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/*
	 * This method gets the filename associated with the book.
	 *
	 */
	public String getFilename() {
		return this.filename;
	}

	/*
	 * This method sets the filename for a book.
	 *
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/*
	 * This method gets the title, author, genre, and filename for a book and
	 * returns it.
	 *
	 */
	@Override
	public String toString() {
		String s = ("Title: " + title + " Author: " + author + " Genre: " + genre + " Filename : " + filename);
		return s;
	}

	/*
	 * This method checks if any book attributes are null and also if the filename
	 * associated with the book is correct and is an actual file.
	 *
	 */
	public boolean isValid() {
		boolean ret = false;
		if (this.title != null && this.author != null && this.genre != null && this.filename != null) {
			File fileInput = new File(this.filename);
			if (fileInput.exists() && fileInput.isFile()) {
				ret = true;
			}
		}
		return ret;
	}

	/*
	 * This methods gets the content of the book and returns it as a string.
	 *
	 */
	public String getText() {
		String line = "";
		// check if file exists
		File fileName = new File(this.filename);
		if (fileName.exists() && fileName.isFile()) {
			try {
				Scanner fileScan = new Scanner(fileName);
				while (fileScan.hasNextLine()) {
					line += fileScan.nextLine() + "\n";
				} // end while
				fileScan.close();
			} // end try
			catch (FileNotFoundException e) // handle error
			{
			} // end catch

		} else {
			System.out.println("This file is not able to be opened.");
		}
		return line;
	}
}