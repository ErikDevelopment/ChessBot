package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InfoCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"about", "a", "i"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) {
        EmbedBuilder builder = new EmbedBuilder();

        String author1 = "FDLroxx#3963";
        String author = event.getJDA().getUserById("542243770113064961").getAsTag();

        builder.setTitle("**Info**");

        builder.addField(new MessageEmbed.Field("Authors", "[" + author + " - Main dev](https://discord.gg/7scMhD6 \"Click to join the official support server\")\n[" + author1 + " - Chess designer](https://discord.gg/7scMhD6 \"Click to join the official support server\")", false));
        builder.addField(new MessageEmbed.Field("Library", "[JDA (4.2.0_168)](https://github.com/DV8FromTheWorld/JDA \"Click to open GitHub repository\")", false));
        builder.addField(new MessageEmbed.Field("GitHub repository", "[Chess Bot (Private for now)](https://github.com/Cyber-Repocord/ChessBot \"Click to open GitHub repository\")", false));
        builder.addField(new MessageEmbed.Field("Stats", "Guilds count: " + event.getJDA().getGuilds().toArray().length + "\nGames being played right now: " + Utils.getPlayedGamesCount() + "\nShards: " + Utils.getShardsCount(), false));

        builder.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());

        builder.setFooter("Last restart");
        builder.setTimestamp(Utils.getRestartDate());

        builder.setColor(new Color(0x0064C8));

        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessage(builder.build()).queue();
    }

    @Override
    public void execute(PrivateMessageReceivedEvent event, String[] args) {
        EmbedBuilder builder = new EmbedBuilder();

        String author1 = "FDLroxx#3963";
        String author = Objects.requireNonNull(event.getJDA().getUserById("542243770113064961")).getAsTag();

        builder.setTitle("**Info**");

        builder.addField(new MessageEmbed.Field("Authors", "[" + author + " - Main dev](https://discord.gg/7scMhD6 \"Click to join the official support server\")\n[" + author1 + " - Graphic designer](https://discord.gg/7scMhD6 \"Click to join the official support server\")", false));
        builder.addField(new MessageEmbed.Field("Library", "[JDA (4.2.0_168)](https://github.com/DV8FromTheWorld/JDA \"Click to open GitHub repository\")", false));
        builder.addField(new MessageEmbed.Field("GitHub repository", "[Chess Bot](https://www.youtube.com/watch?v=dQw4w9WgXcQ \"Click to open GitHub repository\")", false));
        builder.addField(new MessageEmbed.Field("Stats", "Guilds count: " + event.getJDA().getGuilds().toArray().length + "\nGames being played right now: " + Utils.getPlayedGamesCount() + "\nShards: " + Utils.getShardsCount(), false));

        builder.setThumbnail(event.getJDA().getSelfUser().getAvatarUrl());

        builder.setFooter("Last restart");
        builder.setTimestamp(Utils.getRestartDate());

        builder.setColor(new Color(0x0064C8));

        builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());

        event.getChannel().sendMessage(builder.build()).queue();
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
        return !only;
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
