package com.github.cyber_repocord.helperclasses;


import com.github.cyber_repocord.ChessBot;
import com.github.cyber_repocord.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.awt.*;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
    // Private constructor
    private Utils() {}

    // Constants
    private static final String PREFIX = "ch!";
    private static final boolean beta = true;
    private static final String betaChannel = "770219287414571068";
    public static final OffsetDateTime restartDate = OffsetDateTime.now();

    // Fields
    private static final List<Command> commands = new ArrayList<>();
    private static final HashMap<String, Invite> invites = new HashMap<>();
    private static HashMap<String, Game> games = new HashMap<>();

    // Static constructors
    static {
        // Add commands to the list
        commands.add(new HelpCommand());
        commands.add(new InfoCommand());
        commands.add(new SupportCommand());
        commands.add(new StartCommand());
        commands.add(new CancelCommand());
        commands.add(new AcceptCommand());
        commands.add(new DenyCommand());
    }

    // Commands
    public static List<Command> getCommands() {
        return commands;
    }

    // Other
    public static boolean isBeta() {
    return beta;
}
    public static String getBetaChannel() {
        return betaChannel;
    }
    public static String getPrefix() {
        return PREFIX;
    }
    public static OffsetDateTime getRestartDate() {
        return restartDate;
    }
    public static String getToken() {
        return TOKEN;
    }
    public static boolean isMention(Message message) {
        String[] args = message.getContentRaw().split(" ");
        if (args.length < 2) return false;
        if (!Pattern.matches("<@[0-9]{18}>", args[1]) && !Pattern.matches("<@![0-9]{18}>", args[1])) return false;
        for (Member member : message.getMentionedMembers()) {
            return true;
        }
        return false;
    }
    public static String getIDFromMention(Message message) {
        for (Member member : message.getMentionedMembers()) {
            return member.getId();
        }
        return null;
    }

    // Pre-built embeds
    public static EmbedBuilder getIncorrectUsage() {
        final EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Incorrect usage!");
        builder.setDescription("Type " + Utils.getPrefix() + "help <command> to see how to use it.");
        builder.setColor(new Color(0xC80000));
        return builder;
    }
    public static EmbedBuilder getPersonAlreadyHasInvite() {
        final EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Cannot invite!");
        builder.setDescription("Either you or the person already have an outgoing or ingoing invite, cancel it or deny it.");
        builder.setColor(new Color(0xC80000));
        return builder;
    }

    // Game utils
    public static boolean getIfGameExists(String channelID) {
        return games.containsKey(channelID);
    }
    public static Game getGame(String channelID) {
        return games.get(channelID);
    }
    public static void setGame(String channelID, Game game) {
        games.put(channelID, game);
    }
    // Invites utils
    public static boolean doesInviteExist(String key) {
        if (invites.containsKey(key)) return Duration.between(invites.get(key).getTime(), OffsetDateTime.now()).getSeconds() < 5 * 60;
        else return false;
    }
    public static Invite getInvite(String invitee) {
        return invites.get(invitee);
    }
    public static void setInvite(Invite invite) {
        invites.put(invite.getInvitee(), invite);
        invites.put(invite.getInviter(), invite);
    }
    public static void delInvite(Invite invite) {
        if (doesInviteExist(invite.getInviter())) {
            invites.remove(invite.getInviter());
        }
        if (doesInviteExist(invite.getInvitee())) {
            invites.remove(invite.getInvitee());
        }
    }
    // Stats
    public static int getPlayedGamesCount() {
        return 0;
    }
    public static int getShardsCount() {
        return ChessBot.getShardsCount();
    }






























































    private static final String TOKEN = "NzcwMjIwMjY0NDgyMjc1MzM4.X5aZhQ.hrQFX3gh4X4QpaIVCR5vA6Slorg";
}
