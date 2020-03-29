package by.htp.library.controller.impl;

import by.htp.library.bean.User;
import by.htp.library.controller.Command;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;
import by.htp.library.service.UserService;

public class Registraion implements Command {

	private static final String RESPONSE1 = "Error during registration procedure";
	private static final String RESPONSE2 = "Registration completed!";
	private static final String RESPONSE3 = "Sorry! There is a user with the same login!";

	@Override
	public String execute(String request) {

		String response;

		UserService factory = ServiceProvider.getInstance().getUserService();
		User user = new User(extractFirstParam(request), extractSecondParam(request));

		try {
			response = defineResponse(factory.registraion(user));
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

		secondParam = request.substring(request.lastIndexOf(DELIMITER1) + 1, request.lastIndexOf(DELIMITER2));

		return secondParam;

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
