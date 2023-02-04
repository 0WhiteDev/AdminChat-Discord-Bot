package me.whitedev.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Tickets {
    public void ticketCreator(JDA jda, @NotNull SlashCommandInteractionEvent event){
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Create your own Ticket! :tickets:");
        embedBuilder.setDescription(":shield: AdminChat Service - Tickets");
        embedBuilder.addField(":star2: How to Create a Ticket?", "Click on the button at the bottom of the message and go to private chat with the bot", false);
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(jda.retrieveUserById("1070488488593596436").complete().getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        MessageChannel channel = event.getChannel();
        MessageEmbed embed = embedBuilder.build();
        Button ticketButton = Button.primary("create-ticket", "Create Ticket! \uD83C\uDFAB");
        channel.sendMessageEmbeds(embed)
                .addActionRow(ticketButton)
                .queue();
        event.reply("Ticket Creator Gui Successfully Created! :white_check_mark:").setEphemeral(true).queue();
    }
}
