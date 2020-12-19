package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Game;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class MoveCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) throws Exception {
        if (Utils.doesGameExist(event.getAuthor().getId())) {
            Game game = Utils.getGame(event.getAuthor().getId());
            if (!(game.isTurn() && game.getPlayer1().equals(event.getAuthor().getId()))) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Not your turn!");
                builder.setDescription("It's not your turn, wait for another user to move!");
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                builder.setColor(new Color(0xC80000));
                event.getChannel().sendMessage(builder.build()).queue();
                return;
            }
            try {
                if (game.makeMove(args)) {
                    System.out.println("a");
                } else {
                    System.out.println("b");
                }
                game.sendAsEmbed(event);
                Utils.setGame(game);
            } catch (IllegalArgumentException e) {
                event.getChannel().sendMessage(Utils.getIncorrectUsage().build()).queue();
            }
        } else {
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("No game");
            builder.setDescription("You are not playing a game yet!");
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            builder.setColor(new Color(0xC80000));
            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) throws Exception {

    }

    @Override
    public String getName() {
        return "Move Command";
    }

    @Override
    public String getDesc() {
        return "Moves your chess piece";
    }

    @Override
    public String getHelp() {
        // TODO
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
