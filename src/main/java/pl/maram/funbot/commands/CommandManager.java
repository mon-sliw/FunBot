package pl.maram.funbot.commands;

import pl.maram.funbot.commands.basic.HelloCommand;
import pl.maram.funbot.commands.basic.HelpCommand;
import pl.maram.funbot.commands.basic.Magic8BallCommand;
import pl.maram.funbot.commands.model.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {

    static CommandManager instance;
    private List<Command> commands;
    private Map<String, Command> mapCommands;


    public CommandManager() {
        super();
        commands = new ArrayList<>();
        mapCommands = new HashMap<>();

        //todo add commands here
        addCommand(new HelloCommand());
        addCommand(new HelpCommand());
        addCommand(new Magic8BallCommand());
    }

    private void addCommand(Command c) {
        commands.add(c);
        mapCommands.put(c.getName(), c);
    }

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();
        return instance;
    }

    public static List<Command> getCommands() {
        return getInstance().commands;
    }

    public static Command getCommand(String name) {
        return getInstance().mapCommands.get(name);
    }
}
