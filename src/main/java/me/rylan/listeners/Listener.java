package me.rylan.listeners;

import me.rylan.BotConfig;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class Listener extends ListenerAdapter {

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        System.out.println(event.getJDA().getSelfUser().getName() + " is online!");
    }

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Member member = event.getMember();

        if ((event.getMessage().getContentRaw().equalsIgnoreCase(BotConfig.getPrefix() + "shutdown")) && (member.hasPermission(Permission.ADMINISTRATOR))) {
            event.getJDA().shutdown();
            System.exit(0);
        } else if ((event.getMessage().getContentRaw().equalsIgnoreCase(BotConfig.getPrefix() + "shutdown")) && (!member.hasPermission(Permission.ADMINISTRATOR))) {
            event.getMessage().getChannel().sendMessage("Only administrators of " + event.getJDA().getSelfUser().getName() + " can use this command!").queue();
        }
    }
}