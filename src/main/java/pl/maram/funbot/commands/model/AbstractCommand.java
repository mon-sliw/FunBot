package pl.maram.funbot.commands.model;

public abstract class AbstractCommand implements Command {

    String name;

    public AbstractCommand(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
