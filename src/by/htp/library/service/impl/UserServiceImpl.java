package by.htp.library.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.htp.library.bean.User;
import by.htp.library.dao.DaoException;
import by.htp.library.dao.DaoProvider;
import by.htp.library.dao.UserDao;
import by.htp.library.service.ServiceException;
import by.htp.library.service.UserService;

public class UserServiceImpl implements UserService {

	private final static String DELIMITER2 = " ";
	private final static String DELIMITER5 = "login: ";
	private final static String DELIMITER6 = "password: ";

	@Override
	public boolean authorization(User user) throws ServiceException {

		UserDao provider = DaoProvider.getInstance().getUserDao();

		try {

			if (userProfileExistenceCheck(user, provider)) {
				return true;
			}

		} catch (DaoException e) {
			throw new ServiceException("Error! Impossible to sing in!");
		}

		return false;
	}

	@Override
	public boolean registraion(User user) throws ServiceException {
		if (user.getLogin() == null || user.getLogin().isEmpty()) {
			throw new RuntimeException("login is empty");
		}
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new RuntimeException("password is empty");
		}

		UserDao provider = DaoProvider.getInstance().getUserDao();

		try {

			if (userDataCheck(user.getLogin(), provider)) {
				return false;
			} else {
				provider.registraion(user);
				return true;
			}

		} catch (DaoException e) {
			throw new ServiceException("Error! Unable to register user");
		}

	}

	private boolean userDataCheck(String data, UserDao provider) throws DaoException {

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

	private boolean userProfileExistenceCheck(User user, UserDao provider) throws DaoException {

		if (provider.getAvailableUser()
				.contains(DELIMITER5 + user.getLogin() + DELIMITER2 + DELIMITER6 + user.getPassword()))
			return true;

		return false;
	}

}
