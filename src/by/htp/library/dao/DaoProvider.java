package by.htp.library.dao;

import by.htp.library.dao.impl.FileUserDao;
import by.htp.library.dao.impl.FileLibrarianDao;
import by.htp.library.dao.LibrarianDao;
import by.htp.library.dao.UserDao;

public class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();

	private final LibrarianDao fileBookImpl = new FileLibrarianDao();
	private final UserDao fileUserImpl = new FileUserDao();

	private DaoProvider() {
	}

	public static DaoProvider getInstance() {
		return instance;
	}

	public LibrarianDao getBookDao() {
		return fileBookImpl;
	}

	public UserDao getUserDao() {
		return fileUserImpl;
	}

}
