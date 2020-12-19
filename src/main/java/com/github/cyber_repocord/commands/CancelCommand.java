package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Invite;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CancelCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"c"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) throws Exception {
        EmbedBuilder builder = new EmbedBuilder();
        if (Utils.doesInviteExist(event.getAuthor().getId())) {
            Invite invite = Utils.getInvite(event.getAuthor().getId());
            if (!invite.getInviter().equals(event.getAuthor().getId())) {
                builder.setTitle("No invites to cancel");
                builder.setDescription("You don't have outgoing invites!");
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                builder.setColor(new Color(0xC80000));
            } else {
                builder.setTitle("Invite cancelled");
                builder.setDescription("You have cancelled invite to  <@!" + invite.getInviter() + ">.");
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                builder.setColor(new Color(0x0064C8));
                Utils.delInvite(invite);
            }
        } else {
            builder.setTitle("No invites to cancel");
            builder.setDescription("You don't have outgoing invites!");
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            builder.setColor(new Color(0xC80000));
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {

    }

    @Override
    public String getName() {
        return "Cancel Invite Command";
    }

    @Override
    public String getDesc() {
        return "Cancels invitation to game sent by you";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "cancel";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isPrivate(boolean only) {
        return !only;
    }

    @Override
    public void setEnabled(boolean enabled) {
        CancelCommand.enabled = enabled;
    }

    @Override
    public List<String> getAliases() {
        return Arrays.asList(aliases);
    }

    @Override
    public String[] getAliasesAsArray() {
        return aliases;
    }
}
