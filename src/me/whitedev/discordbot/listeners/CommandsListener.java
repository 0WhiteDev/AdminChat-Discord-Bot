package me.whitedev.discordbot.listeners;

import me.whitedev.discordbot.commands.Author;
import me.whitedev.discordbot.commands.Category;
import me.whitedev.discordbot.commands.Tickets;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandsListener extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        JDA jda = event.getJDA();
        Member member = event.getMember();
        switch (event.getName()) {
            case "author" -> {
                Author author = new Author();
                author.authorSocial(jda, event);
            }
            case "tickets" -> {
                assert member != null;
                if (member.isOwner() || member.hasPermission(Permission.ADMINISTRATOR)) {
                    Tickets tickets = new Tickets();
                    tickets.ticketCreator(jda, event);
                }
            }
            case "set-category" -> {
                assert member != null;
                if (member.isOwner() || member.hasPermission(Permission.ADMINISTRATOR)) {
                    Category category = new Category();
                    category.setCategory(jda, event);
                }
            }
        }
    }
}
