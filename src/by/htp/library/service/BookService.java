package by.htp.library.service;

import by.htp.library.bean.Book;

public interface BookService {
	boolean addBook(Book book) throws ServiceException;

	boolean take(Book book) throws ServiceException;

	boolean giveBack(Book book) throws ServiceException;

	String showAllAvailableBooks() throws ServiceException;
}
