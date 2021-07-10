package pl.maram.funbot.commands.basic;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import pl.maram.funbot.commands.model.AbstractCommand;

import java.util.Random;

public class Magic8BallCommand extends AbstractCommand {

    public static final String NAME = "8ball";

    public static final String[] answers = {"placeholder1", "placeholder2", "placeholder3"};

    public Magic8BallCommand() {
        super(NAME);
    }

    @Override
    public void execute(MessageCreateEvent event, Message message) {
        message.getChannel().flatMap(channel -> channel
                .createMessage(getAnswer()))
                .subscribe();
    }

    String getAnswer(){
        Random random = new Random();
        return answers[random.nextInt(answers.length)];
    }
}
