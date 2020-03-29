package by.htp.library.view.validation;

public class ResponseValidation {

	private final static String RESPONSE1 = "You are singed in";
	private final static String RESPONSE2 = "Registration completed!";

	public boolean responseValidation(String response) {

		if (response.equals(RESPONSE1) || response.equals(RESPONSE2))
			return true;

		return false;
	}
}
