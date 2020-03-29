package by.htp.library.controller;

public class Controller {

	private final CommandProvider commandProvider = new CommandProvider();
	private final static char DELIMITER = ' ';

	public String executeTask(String request) {
		String response;
		Command executionCommand;

		executionCommand = findExecutionCommand(request);
		response = executionCommand.execute(request);

		return response;
	}

	private String executeCommand(String request) {
		String commandName;

		commandName = request.substring(0, request.indexOf(DELIMITER));

		return commandName;
	}

	private Command findExecutionCommand(String request) {
		Command executionCommand;

		executionCommand = commandProvider.getCommand(executeCommand(request));

		return executionCommand;
	}
}