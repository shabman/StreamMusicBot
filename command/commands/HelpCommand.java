package me.shab.command.commands;

import me.shab.command.CommandContext;
import me.shab.command.ICommand;
import me.shab.mains.Bot;
import me.shab.mains.CommandManager;
import me.shab.mains.Config;
import me.shab.utils.Embed;

import net.dv8tion.jda.api.entities.TextChannel;

import java.util.List;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public void handle(CommandContext   ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();

        if (args.isEmpty()) {
            StringBuilder builder = new StringBuilder();

            builder.append("List of commands\n");

            manager.getCommands().stream().map(ICommand::getName).forEach(
                    (it) -> builder.append('`').append(Config.get("prefix")).append(it).append("`\n")
            );

            channel.sendMessage(new Embed(
                    Bot.WARNING+" System",
                    builder.toString(),
                    new int[] {242,93,179,255}
                    ).finished()
            ).queue();
            return;
        }

        String search = args.get(0);
        ICommand command = manager.getCommand(search);

        if (command == null) {
            channel.sendMessage(new Embed(
                    Bot.ERROR+" Help Error",
                    Bot.FAILED+" Command not found: " + search,
                    new int[] {242,93,179,255}
                    ).finished()
            ).queue();
            return;
        }

        channel.sendMessage(new Embed(
                Bot.WARNING+"Help: " + command.getName(),
                command.getHelp().toString(),
                new int[]{242,93,179,255}
                ).finished()
        ).queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Displays a list of commands\n" +
                "`Usage:"+Config.get("prefix")+" help`";
    }

    @Override
    public List<String> getAliases() {
        return List.of("commands", "cmds", "opts");
    }
}
