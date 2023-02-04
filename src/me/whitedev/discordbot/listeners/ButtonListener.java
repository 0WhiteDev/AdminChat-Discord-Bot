package me.whitedev.discordbot.listeners;

import me.whitedev.discordbot.Main;
import me.whitedev.discordbot.utils.MessagesUtil;
import me.whitedev.discordbot.utils.TicketsUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class ButtonListener extends ListenerAdapter {

    int tickets = 0;
    TicketsUtil ticketsUtil = new TicketsUtil();
    MessagesUtil messagesUtil = new MessagesUtil();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        User user = event.getUser();
        Guild guild = event.getGuild();
        JDA jda = event.getJDA();
        switch (Objects.requireNonNull(event.getButton().getId())) {
            case "create-ticket" -> createTicket(user, event, guild, jda);
            case "close-ticket" -> closeTicket(event);
            case "remove-ticket" -> removeTicket(event);
        }
    }

    private void createTicket(User user, ButtonInteractionEvent event, Guild guild, JDA jda) {
        if (TicketsUtil.TICKETSMAP.containsKey(user.getIdLong())) {
            event.reply("You already have your ticket :x: " +
                            "\nGo back to chat with the bot to continue talking with the administrator!")
                    .setEphemeral(true)
                    .complete();
        } else if (Main.CATEGORY_ID == null) {
            event.reply("We encountered a small problem, probably the administrator has not yet configured the bot via /set-category, wait until the bot is fully configured or report this bug to the owner! :x:")
                    .setEphemeral(true)
                    .queue();
        } else {
            tickets++;
            ticketsUtil.newTicket(tickets, user, guild, jda);
            event.reply("Your Ticket Created! :white_check_mark: " +
                            "\nTo write with the admins, go to the private channel with the bot and say what you need help with!")
                    .setEphemeral(true)
                    .complete();
        }
    }

    private void closeTicket(ButtonInteractionEvent event) {
        event.reply("Ticket has been successfully closed :lock:").setEphemeral(true).queue();
        TextChannel channel = event.getChannel().asTextChannel();
        User user = event.getJDA().getUserById(findUser(channel.getName()));
        assert user != null;
        messagesUtil.comunicatForUser(user,
                "Your ticket has been closed :lock:",
                "Your ticket has been closed by the administrator, you can no longer reply to messages, if you need further help please open a new ticket");
        TicketsUtil.TICKETSMAP.remove(user.getIdLong());
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("Ticket has been successfully closed :lock:");
        embedBuilder.setDescription("The ticket has been closed, now you can delete the channel with the ticket");
        embedBuilder.setFooter("AdminChat Service © | Bot created by 0whitedev - 2023");
        embedBuilder.setThumbnail(Main.BOT.getAvatarUrl());
        embedBuilder.setColor(Color.MAGENTA);
        MessageEmbed embed = embedBuilder.build();
        Button removeButton = Button.danger("remove-ticket", "Remove Ticket! ❌");
        channel.sendMessageEmbeds(embed)
                .addActionRow(removeButton)
                .queue();
    }

    private void removeTicket(ButtonInteractionEvent event) {
        TextChannel channel = event.getChannel().asTextChannel();
        User user = event.getJDA().getUserById(findUser(channel.getName()));
        if (user != null) {
            messagesUtil.comunicatForUser(user,
                    "Your ticket has been closed :lock:",
                    "Your ticket has been closed by the administrator, you can no longer reply to messages, if you need further help please open a new ticket");
            TicketsUtil.TICKETSMAP.remove(user.getIdLong());
        }
        channel.delete().queue();
    }

    private long findUser(String name) {
        for (Map.Entry<Long, String> entry : TicketsUtil.TICKETSMAP.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return 0;
    }

}
