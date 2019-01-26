import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a class that makes library objects as an ArrayList and defines their
 * attributes and methods.
 *
 * @author Joshua McKerracher
 *
 */

public class Library { // implements library interface
	public LibraryPanel libraryPanel;

	private ArrayList<Book> books;

	/*
	 * Constructor for the library object.
	 * 
	 */
	public Library() {
		this.books = new ArrayList<Book>();
	}

	/*
	 * Gets the number of books in the library using by using the inherited
	 * ArrayList method that gets the size of an ArrayList.
	 * 
	 */
	public int size() {
		return books.size();
	}

	/*
	 * Gets all books in the library by using the inherited ArrayList method that
	 * gets an object in an ArrayList at a certain position. It then adds them to a
	 * second library and returns that library.
	 * 
	 */
	public ArrayList<Book> getBooks() {
		ArrayList<Book> copyBooks = new ArrayList<Book>();
		for (int i = 0; i < this.books.size(); i++) {
			Book b = this.books.get(i);
			copyBooks.add(b);
		}
		return copyBooks;
	}

	/*
	 * Adds a book to the library by using the inherited ArrayList method that adds
	 * an object to an ArrayList.
	 *
	 */
	public void addBook(Book goodBook) {
		this.books.add(goodBook);
	}

	/*
	 * Removes a book from the library by using the inherited ArrayList method that
	 * removes an object from an ArrayList.
	 *
	 */
	// check if there is a book in that position, if there is, remove it:
	public void removeBook(int index) {
		if (books.size() > index && index >= 0) {
			books.remove(index);
		} else {
			System.out.println("There is no book in that location.");
		}
	}

	/*
	 * Gets a specific book in the library by using the inherited ArrayList method
	 * that gets an object in an ArrayList at a certain position.
	 *
	 */
	public Book getBook(int index) {
		if (this.books.size() > index && index >= 0) {
			Book copyBook = this.books.get(index);
			return copyBook;
		} else {
			return null;
		}
	}

	/*
	 * Walks through the books ArrayList and calls the toString() method on each
	 * Book. Returns a String with each book on its own line.
	 *
	 */
	public String toString() {
		String str = new String();
		for (Book b : this.books) {
			str += b.toString();
		}
		return str;
	}

	/*
	 * Loads a library from a CSV file passed in by the user. It also sets all
	 * attributes for each book in the loaded library.
	 *
	 */
	public void loadLibraryFromCSV(String csvFilename) {

		File csvFile = new File(csvFilename);

		if (csvFile.exists() && csvFile.isFile()) {
			try {

				if (books.size() > 0) {
					for (int i = 0; i < books.size(); i++) {
						books.remove(i);
					}
				}

				Scanner fileScan = new Scanner(csvFile);

				while (fileScan.hasNextLine()) {

					String line = fileScan.nextLine();

					Scanner stringScan = new Scanner(line);
					stringScan.useDelimiter(",");

					String title = stringScan.next(); // gets title

					String author = stringScan.next(); // gets author

					String genre = stringScan.next(); // gets genre

					String filepath = stringScan.next(); // gets filename

					Book lineBook = new Book(title, author); // create book objects
					lineBook.setGenre(genre);
					lineBook.setFilename(filepath);

					// add book to library
					books.add(lineBook);

					stringScan.close();

				}
				fileScan.close();

			} catch (FileNotFoundException e) { // handle error

			}

		} else {
			System.out.println("File is not found!");
		}

	}

}