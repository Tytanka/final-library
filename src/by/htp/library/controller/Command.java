package by.htp.library.controller;

public interface Command {

	static final char DELIMITER1 = '=';
		static final char DELIMITER2 = ';';
		static final char DELIMITER3 = ':';
	String execute(String request);

}
