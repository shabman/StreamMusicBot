package me.shab.command.commands;

import me.shab.command.CommandContext;
import me.shab.command.ICommand;
import me.shab.mains.Bot;
import me.shab.utils.Embed;

import net.dv8tion.jda.api.JDA;

import java.util.List;

public class PingCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getExistingCommand().add(this.getName());
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue(
                (ping) -> ctx.getChannel()
                        .sendMessage(new Embed(Bot.WARNING+" System", "Ping:" + ping + "\nWeb socket:" + jda.getGatewayPing(),
                                new int[]{242,93,179,255})
                                .finished()
                        ).queue()
        );
    }

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getHelp() {
        return "Shows the current ping from the bot to the discord servers";
    }
}
