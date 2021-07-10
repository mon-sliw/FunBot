package pl.maram.funbot.commands.basic;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.AbstractCommand;

public class CreateChannelCommand extends AbstractCommand {

    public static final String NAME = "create";

    public CreateChannelCommand(){
        super(NAME);
    }

    @Override
    public void execute(MessageCreateEvent event, Message message) {
        event.getGuild().flatMap(guild -> guild
                .createTextChannel(spec -> {
                    spec.setName("game-for-"+message.getAuthor().orElse(null));
                    //todo spec.setPermissionOverwrites()
                }
                ));
    }
}
