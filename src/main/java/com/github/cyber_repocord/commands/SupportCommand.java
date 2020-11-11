package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SupportCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"su"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setColor(new Color(0x0064C8));
        builder.setTitle("Support");
        builder.setDescription("Click [**here**](https://discord.gg/7scMhD6 \"Click to join CodeSupport++\") to join CodeSupport++ server and ask owner for help.");
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
        builder.setColor(new Color(0x0064C8));
        builder.setTitle("Support");
        builder.setDescription("Click [**here**](https://discord.gg/7scMhD6 \"Click to join CodeSupport++\") to join CodeSupport++ server and ask owner for help.");
        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public String getName() {
        return "Support Command";
    }

    @Override
    public String getDesc() {
        return "Sends you link to support server for the bot";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "support";
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
        SupportCommand.enabled = enabled;
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
