package me.whitedev.discordbot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Author {
    public void authorSocial(JDA jda, @NotNull SlashCommandInteractionEvent event){
        EmbedBuilder embedBuilderr = new EmbedBuilder();
        event.reply("The author of the bot is 0WhiteDev! You can find more projects on his social media! :computer:").setEphemeral(true).queue();
        embedBuilderr.setTitle(":fire: Bot Author: 0WhiteDev! :fire:");
        embedBuilderr.setDescription(":shield: AdminChat Service");
        embedBuilderr.addField(":space_invader: Discord", jda.retrieveUserById("901771057931898900").complete().getName() + "#" + jda.retrieveUserById("901771057931898900").complete().getDiscriminator(), false);
        embedBuilderr.addField(":file_folder: GitHub", "https://github.com/0WhiteDev", false);
        embedBuilderr.addField(":camera_with_flash: Instagram", "@0whitedev", false);
        embedBuilderr.addField(":computer: Programming langs: Java/C#/Python", "If you need an application developer talk with me!", false);
        embedBuilderr.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilderr.setThumbnail(jda.retrieveUserById("901771057931898900").complete().getAvatarUrl());
        embedBuilderr.setColor(Color.MAGENTA);
        MessageEmbed embed = embedBuilderr.build();
        MessageChannel channel = event.getChannel();
        channel.sendMessageEmbeds(embed).queue();
    }
}
