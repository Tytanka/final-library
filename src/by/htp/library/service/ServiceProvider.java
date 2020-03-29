package by.htp.library.service;

import by.htp.library.service.impl.UserServiceImpl;

import by.htp.library.service.impl.BookServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();

	private final BookService BookServiceImpl = new BookServiceImpl();
	private final UserService UserServiceImpl = new UserServiceImpl();

	private ServiceProvider() {
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public UserService getUserService() {
		return UserServiceImpl;
	}

	public BookService getBookService() {
		return BookServiceImpl;
	}

}
