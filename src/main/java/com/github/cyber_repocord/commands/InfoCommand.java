package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Game;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class InfoCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"i"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        int[][] board = Game.getDefaultBoard();
        event.getChannel().sendMessage(Game.getGameAsText(board)).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {

    }

    @Override
    public String getName() {
        return "Info Command";
    }

    @Override
    public String getDesc() {
        return "Shows you info about the bot";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "info";
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
        InfoCommand.enabled = enabled;
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
