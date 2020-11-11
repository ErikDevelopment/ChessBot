package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class MoveCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"m"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        event.getChannel().sendMessage("ok").queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {

    }

    @Override
    public String getName() {
        return "Move Command";
    }

    @Override
    public String getDesc() {
        return "Moves a chess piece from one place to other";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "move";
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isPrivate(boolean only) {
        return false;
    }

    @Override
    public void setEnabled(boolean enabled) {
        MoveCommand.enabled = enabled;
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
