package by.htp.library.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.htp.library.bean.Book;
import by.htp.library.service.BookService;
import by.htp.library.dao.DaoException;
import by.htp.library.dao.DaoProvider;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.service.ServiceException;

public class BookServiceImpl implements BookService {

	private final static String DELIMITER1 = "'";
	private final static String DELIMITER2 = " ";
	private final static String DELIMITER3 = "Author: ";
	private final static String DELIMITER4 = "BookName: ";

	@Override
	public boolean addBook(Book book) throws ServiceException {
		if (book.getAuthor() == null || book.getBookName().isEmpty()) {
			throw new RuntimeException("Author is empty");
		}
		if (book.getBookName() == null || book.getBookName().isEmpty()) {
			throw new RuntimeException("password is empty");
		}
		LibrarianDao provider = DaoProvider.getInstance().getBookDao();

		try {

			if (bookDataCheck(book.getAuthor(), provider)) {
				return false;
			} else {
				provider.addBook(book);
				return true;
			}

		} catch (DaoException e) {
			throw new ServiceException("Error! Unable to register user");
		}

	}

	private boolean bookDataCheck(String data, LibrarianDao provider) throws DaoException {

		Matcher matcher;
		Pattern pattern;

		if (data.length() != 0) {
			pattern = Pattern.compile("" + data + "\\s");
			matcher = pattern.matcher(provider.getFullFile());

			if (matcher.find()) {
				return true;
			}

		}

		return false;
	}

	@Override
	public boolean take(Book book) throws ServiceException {

		DaoProvider provider = DaoProvider.getInstance();
		LibrarianDao LibrarianDao = provider.getBookDao();

		try {
			if (bookExistenceCheckForTaking(book, LibrarianDao)) {
				LibrarianDao.take(book);
				return true;
			}
		} catch (DaoException e) {
			throw new ServiceException("Error!Unable to take this book!");
		}

		return false;
	}

	@Override
	public boolean giveBack(Book book) throws ServiceException {

		DaoProvider provider = DaoProvider.getInstance();
		LibrarianDao LibrarianDao = provider.getBookDao();
		try {
			if (bookExistenceCheckForGivingBack(book, LibrarianDao)) {
				LibrarianDao.giveBack(book);
				return true;
			}
		} catch (DaoException e) {
			throw new ServiceException("Error!Unable to give back this book!");
		}

		return false;
	}

	@Override
	public String showAllAvailableBooks() throws ServiceException {

		DaoProvider provider = DaoProvider.getInstance();
		LibrarianDao LibrarianDao = provider.getBookDao();

		try {
			return LibrarianDao.getAvailableBooks();

		} catch (DaoException e) {
			throw new ServiceException("Error!Unable to show books");
		}
	}

	private boolean bookExistenceCheckForTaking(Book book, LibrarianDao LibrarianDao) throws DaoException {

		if (LibrarianDao.getAvailableBooks().contains(
				DELIMITER3 + book.getAuthor() + DELIMITER2 + DELIMITER4 + DELIMITER1 + book.getBookName() + DELIMITER1))
			return true;

		return false;
	}

	private boolean bookExistenceCheckForGivingBack(Book book, LibrarianDao LibrarianDao) throws DaoException {

		if (LibrarianDao.getTakenBooks().contains(
				DELIMITER3 + book.getAuthor() + DELIMITER2 + DELIMITER4 + DELIMITER1 + book.getBookName() + DELIMITER1))
			return true;

		return false;
	}
}