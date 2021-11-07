package me.shab.command.commands;

import me.shab.command.CommandContext;
import me.shab.command.ICommand;
import me.shab.mains.Bot;
import me.shab.utils.AsyncMongo;
import me.shab.utils.Database;
import me.shab.utils.Embed;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;


public class CreateAccount implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        ctx.getExistingCommand().add(this.getName());
        JDA jda = ctx.getJDA();
        final Message message = ctx.getMessage();
        final TextChannel channel = ctx.getChannel();

        try {
            final AsyncMongo mongo = new AsyncMongo("profiles");

            if (mongo.insertOne(Integer.parseInt(Long.toString(ctx.getAuthor().getIdLong())), new String[] {
                    "streams 0",
                    "favArtists 0",
                    "isArtist false"
            })) {
                channel.sendMessage(new Embed(
                        Bot.CELEBRATE+" Account created",
                        Bot.SUCCESS+" New account created for you\nStreams: `0`",
                        new int[] {
                        242,93,179,255
                }).finished()).queue();
            } else {
                channel.sendMessage(new Embed(
                        Bot.ERROR+" Account error",
                        Bot.FAILED+" Cannot create account",
                        new int[] {
                        242,93,179,255
                }).finished()).queue();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String getName() {
        return "createaccount";
    }

    @Override
    public String getHelp() {
        return "Creates a profile account for stuff";
    }
}
