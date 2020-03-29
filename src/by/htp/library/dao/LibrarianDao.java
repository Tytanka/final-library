package by.htp.library.dao;

import by.htp.library.bean.Book;
import by.htp.library.dao.DaoException;

public interface LibrarianDao {
	void take(Book book) throws DaoException;

	void giveBack(Book book) throws DaoException;

	String getTakenBooks() throws DaoException;

	String getAvailableBooks() throws DaoException;

	boolean addBook(Book book) throws DaoException;

	String getFullFile() throws DaoException;
}
