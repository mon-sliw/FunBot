package pl.maram.funbot.commands.basic;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.AbstractCommand;
import pl.maram.funbot.data.Constants;

public class HelpCommand extends AbstractCommand {

    public static final String NAME = "help";

    public HelpCommand(){
        super(NAME);
    }

    @Override
    public void execute(MessageCreateEvent event, Message message) {
        message.getChannel().flatMap(channel -> channel
                .createMessage(getHelpMessage()))
                .subscribe();
    }

    private String getHelpMessage(){
        StringBuilder builder = new StringBuilder();
        builder.append("```Dostępne polecenia:\n");
        builder.append(Constants.PREFIX).append("help").append("\n");
        builder.append(Constants.PREFIX).append("hello").append("\n");
        builder.append(Constants.PREFIX).append("8ball").append("\n");
        builder.append("```");
        return builder.toString();
    }
}
