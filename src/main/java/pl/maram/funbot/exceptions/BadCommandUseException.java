package pl.maram.funbot.exceptions;

import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.Command;
import pl.maram.funbot.data.Constants;

public class BadCommandUseException implements DiscordException {

    @Override
    public void throwException(Message message, Command command, Object... arguments) {
        message.getAuthor().ifPresent(author -> message.getChannel().flatMap(channel -> channel
                        .createMessage(author.getUsername() + ", " + Constants.PREFIX + command.getName() + " nie używa się w ten sposób."))
                        .subscribe());
    }
}
