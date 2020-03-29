package by.htp.library.controller;

import java.util.HashMap;
import java.util.Map;

import by.htp.library.controller.Command;
import by.htp.library.controller.impl.AddBook;
import by.htp.library.controller.impl.AuthorizationCommand;
import by.htp.library.controller.impl.TakeBook;
import by.htp.library.controller.impl.GiveBackBook;
import by.htp.library.controller.impl.Registraion;
import by.htp.library.controller.impl.WrongRequest;
import by.htp.library.controller.CommandName;
import by.htp.library.controller.impl.ShowBook;

public class CommandProvider {

	private final Map<CommandName, Command> commands = new HashMap<>();

	CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.REGISTRATION, new Registraion());
		commands.put(CommandName.ADD_BOOK, new AddBook());
		commands.put(CommandName.TAKE_BOOK, new TakeBook());
		commands.put(CommandName.GIVE_BACK_BOOK, new GiveBackBook());
		commands.put(CommandName.WRONG_REQUEST, new WrongRequest());
		commands.put(CommandName.SHOW_BOOKS, new ShowBook());
	}

	Command getCommand(String name) {
		CommandName commandName;
		Command command;

		try {
			commandName = CommandName.valueOf(name.toUpperCase());
			command = commands.get(commandName);

		} catch (IllegalArgumentException e) {
			command = commands.get(CommandName.WRONG_REQUEST);
		}

		return command;
	}
}
