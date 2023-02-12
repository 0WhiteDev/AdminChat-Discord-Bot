package me.whitedev.discordbot;

import me.whitedev.discordbot.listeners.ButtonListener;
import me.whitedev.discordbot.listeners.CommandsListener;
import me.whitedev.discordbot.listeners.PrivateMsgListener;
import me.whitedev.discordbot.listeners.TicketsMsgListener;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Main {

    static String TOKEN = "MTA3MDQ4ODQ4ODU5MzU5NjQzNg.GxTgAp.ntOCHZKXu4RfqwLwWGwIiVtvarfMGQKUozjkH0";
    public static String SERVER_ID;
    public static String CATEGORY_ID;
    public static User BOT;

    public static void main(String[] args) {
        try {
            JDA jda = JDABuilder.createDefault(TOKEN)
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .enableIntents(GatewayIntent.GUILD_MEMBERS)
                    .enableIntents(GatewayIntent.GUILD_PRESENCES)
                    .build();
            jda.addEventListener(new ButtonListener());
            jda.addEventListener(new PrivateMsgListener());
            jda.addEventListener(new CommandsListener());
            jda.addEventListener(new TicketsMsgListener());
            jda.updateCommands().addCommands(
                    Commands.slash("author", "Check Bot Author and his SocialMedia"),
                    Commands.slash("set-category", "Set the categories in which tickets are to be created").addOption(OptionType.STRING, "id", "ID of Category"),
                    Commands.slash("tickets", "Create a ticket creator on this channel! (Only for Admins)")
            ).queue();
            jda.getPresence().setPresence(OnlineStatus.ONLINE, Activity.of(Activity.ActivityType.PLAYING, "Bot Version: v1.1"));
            BOT = jda.getSelfUser();
            System.out.println("[DiscordBot - 0whitedev] Bot loaded!");
        } catch (Exception e) {
            System.out.println("[DiscordBot - 0whitedev] Cant load the bot!");
            System.out.println("[DiscordBot - 0whitedev] Generating StackTrace...");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
