package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ShowCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"sh"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) throws IOException, IllegalArgumentException {
        EmbedBuilder builder = new EmbedBuilder();
        if (Utils.doesGameExist(event.getAuthor().getId())) {
            Utils.getGame(event.getAuthor().getId()).sendAsEmbed(event);
        } else {
            builder.setTitle("No game");
            builder.setDescription("You are not playing a game yet!");
            builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
            builder.setColor(new Color(0xC80000));
            event.getChannel().sendMessage(builder.build()).queue();
        }
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {

    }

    @Override
    public String getName() {
        return "Show Game Command";
    }

    @Override
    public String getDesc() {
        return "Shows you your game";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "show";
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
        ShowCommand.enabled = enabled;
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
