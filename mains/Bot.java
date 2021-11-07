package me.shab.mains;

import me.shab.events.MainListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Bot {

    public static final String SUCCESS = ":white_check_mark:";
    public static final String ERROR = ":x:";
    public static final String CELEBRATE = ":tada:";
    public static final String WARNING = ":warning:";
    public static final String FAILED = ":no_entry_sign:";

    private Bot() throws LoginException {
        JDA jda = JDABuilder.createDefault(
                Config.get("TOKEN"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS
                )
                .addEventListeners(new MainListener())
                .setActivity(Activity.listening("ms!help"))
                .build();
    }

    @SuppressWarnings("all")
    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
