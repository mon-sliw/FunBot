package pl.maram.funbot.exceptions;

import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.Command;

public interface DiscordException {

    public void throwException(Message message, Command command, Object... arguments);
}
