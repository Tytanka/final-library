package by.htp.library.dao;

import by.htp.library.bean.User;
import by.htp.library.dao.DaoException;

public interface UserDao {

	boolean registraion(User user) throws DaoException;

	String getFullFile() throws DaoException;

	String getAvailableUser() throws DaoException;
}
