package pl.maram.funbot.commands.basic;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.AbstractCommand;

public class HelloCommand extends AbstractCommand {

    public static final String NAME = "hello";

    public HelloCommand() {
        super(NAME);
    }

    @Override
    public void execute(MessageCreateEvent event, Message message) {
        message.getAuthor().ifPresent(author -> message.getChannel().flatMap(channel -> channel
                .createMessage("Hello " + author.getUsername() + "!"))
        .subscribe());
    }
}
