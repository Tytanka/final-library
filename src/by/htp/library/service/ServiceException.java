package by.htp.library.service;

public class ServiceException extends Exception {
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}
}
