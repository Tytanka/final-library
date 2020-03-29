package by.htp.library.controller.impl;

import by.htp.library.controller.Command;
import by.htp.library.service.*;
import by.htp.library.service.ServiceProvider;

public class ShowBook implements Command {

	private static final String RESPONSE1 = "Error during showing operation!";

	@Override
	public String execute(String request) {

		String response;

		BookService service = ServiceProvider.getInstance().getBookService();

		try {
			response = service.showAllAvailableBooks();
		} catch (ServiceException e) {
			response = RESPONSE1;
		}

		return response;
	}
}
