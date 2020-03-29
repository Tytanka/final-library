package by.htp.library.service;

import by.htp.library.bean.User;

public interface UserService {

	boolean authorization(User user) throws ServiceException;

	boolean registraion(User user) throws ServiceException;

}
