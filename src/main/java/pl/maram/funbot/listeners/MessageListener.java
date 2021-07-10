package pl.maram.funbot.listeners;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.User;
import discord4j.core.object.entity.channel.MessageChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maram.funbot.commands.CommandManager;
import pl.maram.funbot.commands.model.Command;
import pl.maram.funbot.data.Constants;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MessageListener {

    private final static Logger LOG = LogManager.getLogger(MessageListener.class);

    public Mono<MessageChannel> onReady(MessageCreateEvent event) {
        return event.getMessage().getChannel()
                .doOnSuccess(channel -> {
                    if (!event.getMessage().getAuthor().map(User::isBot).orElse(true)) {
                        List<Command> commandsAvailable = new ArrayList<>();

                        for (Command command : CommandManager.getCommands()) {
                            if (event.getMessage().getContent().startsWith(Constants.PREFIX + command.getName()))
                                commandsAvailable.add(command);
                        }

                        if (!commandsAvailable.isEmpty()) {
                            commandsAvailable.sort(Comparator.comparingInt(c -> c.getName().length()));

                            try {
                                commandsAvailable.get(0).execute(event, event.getMessage());
                            } catch (Exception e) {
                                //todo discordException.UNKNOWN_ERROR.report()
                                LOG.error("onReady", e);
                            }
                        }
                    }
                });

    }
}
