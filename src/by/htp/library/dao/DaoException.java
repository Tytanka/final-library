package by.htp.library.dao;

public class DaoException extends Exception {
	private static final long serialVersionUID = 1L;

	public DaoException() {
	}

	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable throwable) {
		super(throwable);
	}

}
