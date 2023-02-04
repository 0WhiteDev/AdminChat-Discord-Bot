package me.whitedev.discordbot.utils;

import me.whitedev.discordbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

import java.awt.*;

public class MessagesUtil {

    public void messageFromUser(User user, String contents, Guild guild) {
        if (user.getIdLong() != Main.BOT.getIdLong()) {
            TextChannel channel = guild.getTextChannelsByName(TicketsUtil.TICKETSMAP.get(user.getIdLong()), true).get(0);
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle("New message from: " + user.getName() + " :speech_balloon:");
            embedBuilder.setDescription(contents);
            embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
            embedBuilder.setThumbnail(user.getAvatarUrl());
            embedBuilder.setColor(Color.MAGENTA);
            MessageEmbed embed = embedBuilder.build();
            channel.sendMessageEmbeds(embed).queue();
        }
    }

    public void messageToUser(User user, String contents) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("New message from Admin :speech_balloon:");
        embedBuilder.setDescription(contents);
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(Main.BOT.getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        user.openPrivateChannel().queue((channel) -> {
            MessageEmbed embed = embedBuilder.build();
            channel.sendMessageEmbeds(embed).queue();
        });
    }

    public void comunicatForUser(User user, String title, String contents) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setDescription(contents);
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(Main.BOT.getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        user.openPrivateChannel().queue((channel) -> {
            MessageEmbed embed = embedBuilder.build();
            channel.sendMessageEmbeds(embed).queue();
        });
    }

}
