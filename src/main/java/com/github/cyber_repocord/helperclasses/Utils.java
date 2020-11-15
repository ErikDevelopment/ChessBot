package com.github.cyber_repocord.helperclasses;


import com.github.cyber_repocord.ChessBot;
import com.github.cyber_repocord.commands.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
        commands.add(new HelpCommand());
        commands.add(new InfoCommand());
        commands.add(new SupportCommand());
        commands.add(new StartCommand());
        commands.add(new CancelCommand());
        commands.add(new AcceptCommand());
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
    public static boolean isMention(String text, Guild guild) {
        if (text.startsWith("<@!") && text.endsWith(">") && text.length() == 22) {
            try {
                long result = Long.parseLong(Objects.requireNonNull(getIDFromMention(text)));
                User user = ChessBot.getJDA().getUserById(result);
                if (user == null) return false;
                else return guild.getMember(user) != null;
            } catch (NullPointerException e) {
                return false;
            }
        } else if (text.startsWith("<@") && text.endsWith(">") && text.length() == 21) {
            try {
                long result = Long.parseLong(Objects.requireNonNull(getIDFromMention(text)));
                User user = ChessBot.getJDA().getUserById(result);
                if (user == null) return false;
                else return guild.getMember(user) != null;
            } catch (NullPointerException e) {
                return false;
            }
        } else return false;
    }
    public static String getIDFromMention(String text) {
        if (text.length() == 22) {
            return text.substring(3, 21);
        } else if (text.length() == 21) {
            return text.substring(2, 20);
        } else {
            return null;
        }
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
        builder.setDescription("The person already has an ingoing or ongoing invite, tell him to cancel it or deny it.");
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
    public static boolean isInviteValid(String invitee, String inviter) {
        if (invites.containsKey(invitee)) {
            Invite invite = invites.get(invitee);
            return invite.getInviter().equals(inviter);
        } else {
            return false;
        }
    }
    public static Invite getInvite(String invitee) {
        return invites.get(invitee);
    }
    public static void setInvite(Invite invite) {
        invites.put(invite.getInvitee(), invite);
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
