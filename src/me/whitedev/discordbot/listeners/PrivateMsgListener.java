package me.whitedev.discordbot.listeners;

import me.whitedev.discordbot.Main;
import me.whitedev.discordbot.utils.MessagesUtil;
import me.whitedev.discordbot.utils.TicketsUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PrivateMsgListener extends ListenerAdapter {

    MessagesUtil messagesUtil = new MessagesUtil();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        Guild guild = jda.getGuildById(Main.SERVER_ID);
        User user = event.getAuthor();
        if (event.isFromType(ChannelType.PRIVATE) && TicketsUtil.TICKETSMAP.get(user.getIdLong()) != null) {
            messagesUtil.messageFromUser(user, event.getMessage().getContentRaw(), guild);
        }else {
            if (event.isFromType(ChannelType.PRIVATE) && user.getIdLong() != Main.BOT.getIdLong()) {
                event.getChannel().asPrivateChannel().sendMessage("You don't have any open tickets, go back to the server and open your ticket! :tickets:").queue();
            }
        }
    }
}
