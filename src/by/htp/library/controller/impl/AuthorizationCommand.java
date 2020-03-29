package by.htp.library.controller.impl;

import by.htp.library.bean.User;
import by.htp.library.controller.Command;
import by.htp.library.service.UserService;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceProvider;

public class AuthorizationCommand implements Command {

	private static final String RESPONSE1 = "Error during sing in procedure!";
	private static final String RESPONSE2 = "You are singed in";
	private static final String RESPONSE3 = "There is no user with this login or password!";

	@Override
	public String execute(String request) {

		String response;

		UserService service = ServiceProvider.getInstance().getUserService();
		User user = new User(extractFirstParam(request), extractSecondParam(request));
		try {
			response = defineResponse(service.authorization(user));

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
		String response;

		if (result == true)
			response = RESPONSE2;
		else
			response = RESPONSE3;

		return response;
	}
}
