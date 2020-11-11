package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Game;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.Arrays;
import java.util.List;

public class StartCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"s"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {

        int[][] board = Game.getDefaultBoard();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Chess Game");
        builder.setDescription(Game.getGameAsText(board));
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {

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
        return false;
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
