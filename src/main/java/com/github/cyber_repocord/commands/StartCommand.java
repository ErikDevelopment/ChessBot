package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Invite;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import javax.rmi.CORBA.Util;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class StartCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"s"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        EmbedBuilder builder = new EmbedBuilder();
        if (args.length == 2) {
            if (Utils.isMention(event.getMessage())) {
                if (Utils.doesInviteExist(Utils.getIDFromMention(event.getMessage())) || Utils.doesInviteExist(event.getAuthor().getId())) {
                    builder = Utils.getPersonAlreadyHasInvite();
                    builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                } else {
                    builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                    builder.setTitle("Waiting for person to accept the invite");
                    builder.setDescription("Waiting for " + args[1] + " to accept the invite with \"" + Utils.getPrefix() + "accept\", if you want to cancel invite type \"" + Utils.getPrefix() + "cancel\"\n(Note: the game chooses randomly whose turn is it, if you want to change it type \"" + Utils.getPrefix() + "start <user> <turn>\")");
                    builder.setColor(new Color(0x0064C8));
                    Utils.setInvite(new Invite(event.getAuthor().getId(), Utils.getIDFromMention(event.getMessage()), false, true));
                }
            } else {
                builder = Utils.getIncorrectUsage();
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            }
        } else if (args.length >= 3) {
            if (Utils.isMention(event.getMessage())) {
                if (Utils.doesInviteExist(Utils.getIDFromMention(event.getMessage())) || Utils.doesInviteExist(event.getAuthor().getId())) {
                    builder = Utils.getPersonAlreadyHasInvite();
                    builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                } else {
                    if (args[2].equalsIgnoreCase("true")) {
                        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                        builder.setTitle("Waiting for person to accept the invite");
                        builder.setDescription("Waiting for " + args[1] + " to accept the invite with \"" + Utils.getPrefix() + "accept " + event.getAuthor().getAsMention() + "\", if you want to cancel invite type \"" + Utils.getPrefix() + "cancel\"\n(Note: the person inviting you will be moving first)");
                        builder.setColor(new Color(0x0064C8));
                        Utils.setInvite(new Invite(event.getAuthor().getId(), Utils.getIDFromMention(event.getMessage()), true, false));
                    } else if (args[2].equalsIgnoreCase("false")) {
                        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                        builder.setTitle("Waiting for person to accept the invite");
                        builder.setDescription("Waiting for " + args[1] + " to accept the invite with \"" + Utils.getPrefix() + "accept " + event.getAuthor().getAsMention() + "\", if you want to cancel invite type \"" + Utils.getPrefix() + "cancel\"\n(Note: the person accepting the invite will be moving first)");
                        builder.setColor(new Color(0x0064C8));
                        Utils.setInvite(new Invite(event.getAuthor().getId(), Utils.getIDFromMention(event.getMessage()), true, false));
                    } else {
                        builder = Utils.getIncorrectUsage();
                        builder.setAuthor(event.getAuthor().getId(), null, event.getAuthor().getAvatarUrl());
                    }
                }
            } else {
                builder = Utils.getIncorrectUsage();
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            }
        } else {
            builder = Utils.getIncorrectUsage();
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        }
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("This feature is not working yet.").queue();
    }

    @Override
    public String getName() {
        return "Start Command";
    }

    @Override
    public String getDesc() {
        return "Starts a game of chess";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "start";
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
        StartCommand.enabled = enabled;
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
