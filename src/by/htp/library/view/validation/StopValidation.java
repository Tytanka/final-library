package by.htp.library.view.validation;

public class StopValidation {

	private final static String RESPONSE1 = " Good bye!";

	public boolean responseValidation(String response) {

		if (response.equals(RESPONSE1))
			return true;

		return false;
	}
}
