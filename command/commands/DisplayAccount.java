package me.shab.command.commands;

import me.shab.command.CommandContext;
import me.shab.command.ICommand;
import me.shab.mains.Bot;
import me.shab.utils.Database;
import me.shab.utils.Embed;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public class DisplayAccount implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getExistingCommand().add(this.getName());
        JDA jda = ctx.getJDA();
        final Message message = ctx.getMessage();
        final TextChannel channel = ctx.getChannel();

        Database acc = new Database("Discord", "profiles");

        if (acc.findData(Long.toString(message.getAuthor().getIdLong()), "_id").equals("NULL")) {
            channel.sendMessage(new Embed(
                    Bot.ERROR+" Display Account error",
                    Bot.WARNING+" Account is null run `!createaccount`",
                    new int[] {
                    242,93,179,255
            }).finished()).queue();
        } else {
            channel.sendMessage(new Embed(
                    Bot.SUCCESS+" Display Account",
                    "Streams: " + (String) acc.findData(
                    Long.toString(message.getAuthor().getIdLong()),
                    "streams")
                    + "\n" + "Favourite Artists: " + (String) acc.findData(
                            Long.toString(message.getAuthor().getIdLong()), "favArtists")
                    + "\n" + "Is Artist: " + (String) acc.findData(
                            Long.toString(message.getAuthor().getIdLong()), "isArtist"
                    ), new int[] {
                    242,93,179,255
            }).finished()).queue();
        }
    }

    @Override
    public String getName() {
        return "displayaccount";
    }

    @Override
    public String getHelp() {
        return "Shows your profile account made with this bot";
    }
}
