package by.htp.library.dao.impl;

import by.htp.library.bean.Book;
import by.htp.library.dao.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLibrarianDao implements LibrarianDao {

	private final static String DELIMITER1 = "'";
	private final static String DELIMITER3 = ";";
	private final static String INFO = "";
	private final static String PATH1 = "resource\\availableBooks.txt";
	private final static String PATH2 = "resource\\takenBooks.txt";

	@Override
	public boolean addBook(Book book) throws DaoException {

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(PATH1), true)))) {

			pw.printf("\nAuthor: %s BookName: '%s' Cover: %s;", book.getAuthor(), book.getBookName(), book.getCover());
			return true;

		} catch (IOException e) {
			throw new DaoException("Impossible to write to this file!");
		}
	}

	@Override
	public String getFullFile() throws DaoException {
		try {
			return readFile();
		} catch (IOException e) {
			throw new DaoException("Impossible to read this file");
		}
	}

	private String readFile() throws IOException {

		StringBuilder stringBuilder = new StringBuilder();

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(PATH1))) {

			String tmp;
			while ((tmp = bufferedReader.readLine()) != null)
				stringBuilder.append(tmp);
		}

		return stringBuilder.toString();
	}

	@Override
	public void take(Book book) throws DaoException {

		try {

			deleteFromFile(book, PATH1);
			writeInfoToFile(book, PATH2, INFO, true);

		} catch (IOException e) {
			throw new DaoException("Error! Unavailable to write to the file");
		}
	}

	@Override
	public void giveBack(Book book) throws DaoException {

		try {

			deleteFromFile(book, PATH2);
			writeInfoToFile(book, PATH1, INFO, true);

		} catch (IOException e) {
			throw new DaoException("Error! Unavailable to write to the file");
		}
	}

	@Override
	public String getAvailableBooks() throws DaoException {

		try {

			return readInfoFromFile(PATH1);

		} catch (IOException e) {
			throw new DaoException("Error! Unavailable to read this file!");
		}
	}

	@Override
	public String getTakenBooks() throws DaoException {

		try {

			return readInfoFromFile(PATH2);

		} catch (IOException e) {
			throw new DaoException("Error! Unavailable to read this file!");
		}
	}

	private void writeInfoToFile(Book book, String fileName, String info, boolean append) throws IOException {

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName), append)))) {

			if (info.length() == 0)
				pw.printf("\nAuthor: %s BookName: '%s' Cover: %s;", book.getAuthor().trim(), book.getBookName().trim(), book.getCover().trim());
			if (info.length() != 0) {
				for (String element : info.split(DELIMITER3))
					pw.printf("\n%s;", element.trim());
			}
		}
	}

	private String readInfoFromFile(String fileName) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String tmp;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

			while ((tmp = bufferedReader.readLine()) != null)
				stringBuilder.append(tmp);

		}

		return stringBuilder.toString();
	}

	private void deleteFromFile(Book book, String fileName) throws IOException {

		StringBuilder file = new StringBuilder(readInfoFromFile(fileName));

		file.delete(file.indexOf(book.getAuthor()), file.indexOf(book.getAuthor()) + book.getAuthor().length());
		file.delete(file.indexOf(DELIMITER1 + book.getBookName() + DELIMITER1),
				file.indexOf(DELIMITER1 + book.getBookName() + DELIMITER1)
						+ (DELIMITER1 + book.getBookName() + DELIMITER1).length());
		file.delete(file.indexOf(book.getCover()), file.indexOf(book.getCover()) + (book.getCover()+ DELIMITER3).length());
		writeInfoToFile(book, fileName, file.toString().trim(), false);
	}

}
