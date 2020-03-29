package by.htp.library.dao.impl;

import by.htp.library.bean.User;
import by.htp.library.dao.*;

import java.io.*;

public class FileUserDao implements UserDao {

	private final static String PATH1 = "resource//users.txt";

	@Override
	public boolean registraion(User user) throws DaoException {

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(PATH1), true)))) {

			pw.printf("\n id: %s login: %s password: %s;", user.getId(), user.getLogin(), user.getPassword());
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

	private String readInfoFromFile(String fileName) throws IOException {

		StringBuilder stringBuilder = new StringBuilder();
		String tmp;

		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {

			while ((tmp = bufferedReader.readLine()) != null)
				stringBuilder.append(tmp);

		}

		return stringBuilder.toString();
	}

	@Override
	public String getAvailableUser() throws DaoException {

		try {

			return readInfoFromFile(PATH1);

		} catch (IOException e) {
			throw new DaoException("Error! Unavailable to read this file!");
		}
	}

}
