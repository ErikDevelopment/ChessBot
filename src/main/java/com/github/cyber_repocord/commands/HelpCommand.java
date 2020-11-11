package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.ChessBot;
import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;
import java.util.List;

public class HelpCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"h"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        if (args.length == 1) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            builder.setColor(new Color(0x0064C8));
            builder.setTitle("Help menu: ");
            builder.setDescription("See list of commands, if you need help with anything else do \"" + Utils.getPrefix() + "support\".");
            for (Command command : ChessBot.getCommands()) {
                builder.addField(new MessageEmbed.Field(command.getName(), Utils.getPrefix() + command.getCommand(), false));
            }
            builder.setFooter("For more info about certain command, do \"" + Utils.getPrefix() + "help <command>\".");
            event.getChannel().sendMessage(builder.build()).queue();
        } else {

            Command command = null;
            for (Command command1 : ChessBot.getCommands()) {
                if (args[1].equalsIgnoreCase(command1.getCommand()) || args[1].equalsIgnoreCase(Utils.getPrefix() + command1.getCommand()) || command1.getAliases().contains(args[1].toLowerCase())) {
                    command = command1;
                } else if (args[1].length() >= Utils.getPrefix().length()) {
                    if (args[1].startsWith(Utils.getPrefix())) {
                        String thing = args[1].substring(Utils.getPrefix().length());
                        if (command1.getAliases().contains(thing.toLowerCase())) {
                            command = command1;
                        }
                    }
                }
            }
            if (command != null) {
                if (command.isEnabled()) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                    builder.setColor(new Color(0x0064C8));
                    builder.setTitle(command.getName() + " (" + Utils.getPrefix() + command.getCommand() + ")");
                    builder.setDescription("**Description:**\n" + command.getDesc() + "\n\n**Help:**\n" + command.getHelp() + "\n\n**Aliases:**\n" + Arrays.toString(command.getAliasesAsArray()));
                    builder.addField(new MessageEmbed.Field("Works on DMs", command.isPrivate(false) ? "true" : "false", true));
                    builder.addField(new MessageEmbed.Field("Works on Guild", !command.isPrivate(true) ? "true" : "false", true));
                    event.getChannel().sendMessage(builder.build()).queue();
                } else {
                    sendCommandNotFound(event);
                }
            } else {
                sendCommandNotFound(event);
            }
        }

    }
    private static void sendCommandNotFound(GuildMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setTitle("Can't find that command!");
        builder.setDescription("Try " + Utils.getPrefix() + "help to get list of commands.");
        builder.setColor(new Color(0xC80000));
        event.getChannel().sendMessage(builder.build()).queue();
    }
    private static void sendCommandNotFound(PrivateMessageReceivedEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setTitle("Can't find that command!");
        builder.setDescription("Try " + Utils.getPrefix() + "help to get list of commands.");
        builder.setColor(new Color(0xC80000));
        event.getChannel().sendMessage(builder.build()).queue();
    }
    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {
        if (args.length == 1) {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            builder.setColor(new Color(0x0064C8));
            builder.setTitle("Help menu: ");
            builder.setDescription("See list of commands, if you need help with anything else do \"" + Utils.getPrefix() + "support\".");
            for (Command command : ChessBot.getCommands()) {
                builder.addField(new MessageEmbed.Field(command.getName(), Utils.getPrefix() + command.getCommand(), false));
            }
            builder.setFooter("For more info about certain command, do \"" + Utils.getPrefix() + "help <command>\".");
            event.getChannel().sendMessage(builder.build()).queue();
        } else {

            Command command = null;
            for (Command command1 : ChessBot.getCommands()) {
                if (args[1].equalsIgnoreCase(command1.getCommand()) || args[1].equalsIgnoreCase(Utils.getPrefix() + command1.getCommand()) || command1.getAliases().contains(args[1].toLowerCase())) {
                    command = command1;
                } else if (args[1].length() >= Utils.getPrefix().length()) {
                    if (args[1].startsWith(Utils.getPrefix())) {
                        String thing = args[1].substring(Utils.getPrefix().length());
                        if (command1.getAliases().contains(thing.toLowerCase())) {
                            command = command1;
                        }
                    }
                }
            }
            if (command != null) {
                if (command.isEnabled()) {
                    EmbedBuilder builder = new EmbedBuilder();
                    builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                    builder.setColor(new Color(0x0064C8));
                    builder.setTitle(command.getName() + " (" + Utils.getPrefix() + command.getCommand() + ")");
                    builder.setDescription("**Description:**\n" + command.getDesc() + "\n\n**Help:**\n" + command.getHelp() + "\n\n**Aliases:**\n" + Arrays.toString(command.getAliasesAsArray()));
                    builder.addField(new MessageEmbed.Field("Works on DMs", command.isPrivate(false) ? "true" : "false", true));
                    builder.addField(new MessageEmbed.Field("Works on Guild", !command.isPrivate(true) ? "true" : "false", true));
                    event.getChannel().sendMessage(builder.build()).queue();
                } else {
                    sendCommandNotFound(event);
                }
            } else {
                sendCommandNotFound(event);
            }
        }
    }

    @Override
    public String getName() {
        return "Help Command";
    }

    @Override
    public String getDesc() {
        return "Shows you help";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isPrivate(boolean only) {
        if (only) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        HelpCommand.enabled = enabled;
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
