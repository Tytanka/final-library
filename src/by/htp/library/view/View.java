package by.htp.library.view;

import by.htp.library.controller.Controller;
import by.htp.library.view.validation.ResponseValidation;
import by.htp.library.view.validation.StopValidation;

public class View {

	public static void main(String[] args) {

		InformationInput informationInput = new InformationInput();

		Controller controller = new Controller();

		ResponseValidation responseValidation = new ResponseValidation();

		String response;

		StopValidation stopValidation = new StopValidation();

		do {
			userMenuText();
			response = controller.executeTask(userMenuRequestCreating(informationInput));
			System.out.println(response);

		} while (!responseValidation.responseValidation(response));

		do {
			operationsMenuText();
			response = controller.executeTask(operationMenuCreating(informationInput));
			System.out.println(response);

		} while (!stopValidation.responseValidation(response));

	}

	public static void userMenuText() {
		System.out.println("Welcome to the library");
		System.out.println("1 - Sing in");
		System.out.println("2 - Registration");
		System.out.println("Your choice?");
	}

	private static void loginInputText() {
		System.out.println("Enter login");
	}

	private static void passwordInputText() {
		System.out.println("Enter password");
	}

	public static String userMenuRequestCreating(InformationInput informationInput) {

		StringBuilder request = new StringBuilder();

		switch (informationInput.intInput()) {
		case 1:
			request.append("AUTHORIZATION ");
			break;
		case 2:
			request.append("REGISTRATION ");
			break;
		default:
			return "WRONG_REQUEST Illegal input!Try again!;";
		}

		request.append("login=");
		loginInputText();
		request.append(informationInput.stringInput());
		request.append("; ");
		request.append("password=");
		passwordInputText();
		request.append(informationInput.stringInput());
		request.append(";");

		return request.toString();
	}

	public static void operationsMenuText() {
		System.out.println("Library menu:");
		System.out.println("1 - Show available books");
		System.out.println("2 - Take the book");
		System.out.println("3 - Give back the book");
		System.out.println("4 - Add the book");
		System.out.println("5 - Exit");
		System.out.println("Your choice?");
	}

	private static void bookNameText() {
		System.out.println("Enter book:");
	}

	private static void authorNameText() {
		System.out.println("Enter author:");
	}

	private static void coverNameText() {
		System.out.println("Enter kind of cover:");
	}
	public static String operationMenuCreating(InformationInput informationInput) {

		StringBuilder request = new StringBuilder();

		switch (informationInput.intInput()) {
		case 1:
			System.out.println();
			return ("SHOW_BOOKS show");
		case 2:
			request.append("TAKE_BOOK ");
			break;
		case 3:
			request.append("GIVE_BACK_BOOK ");
			break;
		case 4:
			request.append("ADD_BOOK ");
			break;
		case 5:
			return "WRONG_REQUEST Good bye!;";
		default:
			return "WRONG_REQUEST Illegal input!Try again!;";
		}

		authorNameText();
		request.append("author=");
		request.append(informationInput.stringInput());
		request.append("; ");

		bookNameText();
		request.append("book:");
		request.append(informationInput.stringInput());
		request.append(":");
        
		coverNameText();
		request.append("cover=");
		request.append(informationInput.stringInput());
		request.append("; ");
		
		return request.toString();
	}

}
