package me.whitedev.discordbot.utils;

import me.whitedev.discordbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketsUtil {

    public static Map<Long, String> TICKETSMAP = new HashMap<>();

    public void newTicket(int tickets, User user, Guild guild, JDA jda) {
        boolean channelFound = false;
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("A New Ticket has been Created! :white_check_mark:");
        embedBuilder.setDescription(":shield: AdminChat Service - " + guild.getName());
        embedBuilder.addField(":tickets: Ticket:", String.format("%03d", tickets), false);
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(Main.BOT.getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        user.openPrivateChannel().queue((channel) -> {
            MessageEmbed embed = embedBuilder.build();
            channel.sendMessageEmbeds(embed).queue();
        });
        TICKETSMAP.put(user.getIdLong(), "ticket-" + String.format("%03d", tickets));
        System.out.println(TICKETSMAP);
        guild.createTextChannel("ticket-" + String.format("%03d", tickets), guild.getCategoryById(Main.CATEGORY_ID)).queue();
        while (!channelFound) {
            List<TextChannel> channels = guild.getTextChannelsByName("ticket-" + String.format("%03d", tickets), true);
            if (channels.size() > 0) {
                TextChannel channel = channels.get(0);
                ticketManager(channel, jda, tickets, user);
                channelFound = true;
            }
        }
    }

    public void ticketManager(MessageChannel channel, JDA jda, int tickets, User user) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Ticket Manager! :tickets:");
        embedBuilder.setDescription(":shield: AdminChat Service - Ticket#" + String.format("%03d", tickets));
        embedBuilder.addField(":bust_in_silhouette: Ticket Owner:", user.getName(), false);
        embedBuilder.addField(":gear: Ticket functions:", "To chat with a user start texting in this channel and the user will get your every message, if you want to delete or close a ticket just click the button below!", false);
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(Main.BOT.getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        MessageEmbed embed = embedBuilder.build();
        Button closeButton = Button.primary("close-ticket", "Close Ticket! \uD83D\uDD12");
        Button removeButton = Button.danger("remove-ticket", "Remove Ticket! ❌");
        channel.sendMessageEmbeds(embed)
                .addActionRow(closeButton)
                .addActionRow(removeButton)
                .queue();
    }
}
