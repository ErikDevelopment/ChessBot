package com.github.cyber_repocord.events;

import com.github.cyber_repocord.ChessBot;
import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommandEventListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        List<Command> commands = ChessBot.getCommands();
        String[] args = event.getMessage().getContentRaw().split(" ");
        String commandAsText = args[0];
        if (event.getAuthor().getId().equals(ChessBot.getBotID())) return;
        if (Utils.isBeta() && !event.getChannel().getId().equals(Utils.getBetaChannel())) {
            return;
        }
        if (commandAsText.length() >= Utils.getPrefix().length()) {
            commandAsText = commandAsText.substring(0, Utils.getPrefix().length());
            if (!commandAsText.equalsIgnoreCase(Utils.getPrefix())) {
                return;
            } else {
                commandAsText = args[0].substring(Utils.getPrefix().length());
            }
        } else {
            return;
        }
        for (Command command : commands) {
            if (command.isEnabled() && !command.isPrivate(true) && (command.getCommand().equalsIgnoreCase(commandAsText) || command.getAliases().contains(commandAsText.toLowerCase()))) command.execute(event, args);
        }
    }

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        List<Command> commands = ChessBot.getCommands();
        String[] args = event.getMessage().getContentRaw().split(" ");
        String commandAsText = args[0];
        if (event.getAuthor().getId().equals(ChessBot.getBotID())) return;
        if (commandAsText.length() >= Utils.getPrefix().length()) {
            commandAsText = commandAsText.substring(0, Utils.getPrefix().length());
            if (!commandAsText.equalsIgnoreCase(Utils.getPrefix())) {
                return;
            } else {
                commandAsText = args[0].substring(Utils.getPrefix().length());
            }
        } else {
            return;
        }
        for (Command command : commands) {
            if (command.isEnabled() && command.isPrivate(false) && (command.getCommand().equalsIgnoreCase(commandAsText) || command.getAliases().contains(commandAsText.toLowerCase()))) command.execute(event, args);
        }
    }
}
