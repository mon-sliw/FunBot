package pl.maram.funbot;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.lifecycle.ReadyEvent;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.maram.funbot.listeners.MessageListener;

public class FunBot {

    static Logger logger = LogManager.getLogger(FunBot.class);

    public static void main(String[] args) {
        GatewayDiscordClient client = DiscordClientBuilder.create(args[0])
                .build()
                .login()
                .block();

        client.getEventDispatcher().on(ReadyEvent.class)
                .subscribe(event -> {
                    final User self = event.getSelf();
                    System.out.printf("Logged in as %s#%s%n", self.getUsername(), self.getDiscriminator());
                });

        /*client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!hello"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Hello world!"))
                .subscribe();
        */
        MessageListener messageListener = new MessageListener();
        client.getEventDispatcher().on(MessageCreateEvent.class).flatMap(messageListener::onReady).subscribe();

       /* client.getEventDispatcher().on(MessageCreateEvent.class)
                .map(MessageCreateEvent::getMessage)
                .filter(message -> message.getAuthor().map(user -> !user.isBot()).orElse(false))
                .filter(message -> message.getContent().equalsIgnoreCase("!help"))
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("```Dostępne polecenia:\n!help\n!hello```"))
                .subscribe();
        */
        client.onDisconnect().block();
    }
}
