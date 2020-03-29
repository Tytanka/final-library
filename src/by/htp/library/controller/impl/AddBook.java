package by.htp.library.controller.impl;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.BookService;

public class AddBook implements Command {
	private static final String RESPONSE1 = "Error during registration procedure";
	private static final String RESPONSE2 = "Registration completed!";
	private static final String RESPONSE3 = "Sorry! Such  book already exists!";

	@Override
	public String execute(String request) {

		String response;

		BookService factory = ServiceProvider.getInstance().getBookService();
		Book book = new Book(extractFirstParam(request), extractSecondParam(request),extractThirdParam(request));

		try {
			response = defineResponse(factory.addBook(book));
		} catch (ServiceException e) {
			response = RESPONSE1;
		}

		return response;
	}

	private String extractFirstParam(String request) {
		String firstParam;

		firstParam = request.substring(request.indexOf(DELIMITER1) + 1, request.indexOf(DELIMITER2));

		return firstParam;
	}

	private String extractSecondParam(String request) {
		String secondParam;

		secondParam = request.substring(request.indexOf(DELIMITER3) + 1, request.lastIndexOf(DELIMITER3));

		return secondParam;
	}
	private String extractThirdParam(String request) {
		String thirdParam;

		thirdParam = request.substring(request.lastIndexOf(DELIMITER1) + 1, request.lastIndexOf(DELIMITER2));

		return thirdParam;
	}

	private String defineResponse(boolean result) {

		String response = "";

		if (result == true)
			response = RESPONSE2;
		if (result == false)
			response = RESPONSE3;

		return response;
	}
}
