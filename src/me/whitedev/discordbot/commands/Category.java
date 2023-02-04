package me.whitedev.discordbot.commands;

import me.whitedev.discordbot.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Category {
    public void setCategory(JDA jda, @NotNull SlashCommandInteractionEvent event){
        String[] args = event.getCommandString().split(" ");
        Main.CATEGORY_ID = args[2];
        Main.SERVER_ID = event.getGuild().getId();
        event.reply("The category id for tickets was set as: " + Main.CATEGORY_ID + " :white_check_mark:").setEphemeral(true).queue();
    }
}
