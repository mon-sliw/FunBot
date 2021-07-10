package pl.maram.funbot.commands.model;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;

public interface Command {

    String getName();

    void execute(MessageCreateEvent event, Message message);

}
