package me.whitedev.discordbot.listeners;

import me.whitedev.discordbot.Main;
import me.whitedev.discordbot.utils.MessagesUtil;
import me.whitedev.discordbot.utils.TicketsUtil;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import scala.util.parsing.combinator.testing.Str;

import java.util.Map;

public class TicketsMsgListener extends ListenerAdapter {

    MessagesUtil messagesUtil = new MessagesUtil();

    @Override
    public void onMessageReceived(MessageReceivedEvent event){
        TextChannel ticketChannel = null;
        if(!event.isFromType(ChannelType.PRIVATE)) {
            ticketChannel = event.getChannel().asTextChannel();
            User user = event.getJDA().getUserById(findUser(ticketChannel.getName()));
            User admin = event.getAuthor();
            if (user != null && admin.getIdLong() != Main.BOT.getIdLong() && event.isFromType(ChannelType.TEXT)) {
                messagesUtil.messageToUser(user, event.getMessage().getContentRaw());
            }
        }
    }

    private long findUser(String name){
        for (Map.Entry<Long, String > entry : TicketsUtil.TICKETSMAP.entrySet()) {
            if (entry.getValue().equals(name)) {
                return entry.getKey();
            }
        }
        return 0;
    }

}
