package by.htp.library.view;

import java.util.Scanner;

public class InformationInput {

	@SuppressWarnings("resource")
	public int intInput() {

		Scanner scanner;
		int number;

		scanner = new Scanner(System.in);

		if (scanner.hasNextInt())
			number = scanner.nextInt();
		else
			number = 0;

		return number;
	}

	@SuppressWarnings("resource")
	public String stringInput() {

		Scanner scanner;
		String data;

		scanner = new Scanner(System.in);
		data = scanner.nextLine();

		return data;
	}

}
