package me.shab.events;

import me.duncte123.botcommons.BotCommons;

import me.shab.mains.Bot;
import me.shab.mains.CommandManager;
import me.shab.mains.Config;
import me.shab.utils.Embed;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class MainListener extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();

        if (user.isBot() || event.isWebhookMessage()) {
            return;
        }

        String raw = event.getMessage().getContentRaw();
        final String PREFIX = Config.get("PREFIX");

        if (raw.equalsIgnoreCase(PREFIX + "shutdown") && user.getId().equals(Config.get("OWNER"))) {

            event.getChannel().sendMessage(new Embed(Bot.WARNING+" System", "Bot shutting down", new int[]{242,93,179,255})
                    .finished())
                    .queue();

            event.getJDA().shutdown();
            BotCommons.shutdown(event.getJDA());
        }
        if (raw.startsWith(Config.get("PREFIX"))) {
            manager.handle(event);
        }
    }
}
