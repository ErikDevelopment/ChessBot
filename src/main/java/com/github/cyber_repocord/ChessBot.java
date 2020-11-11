package com.github.cyber_repocord;

import com.github.cyber_repocord.commands.*;
import com.github.cyber_repocord.events.CommandEventListener;
import com.github.cyber_repocord.events.ReadyEventListener;
import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.util.ArrayList;
import java.util.List;

public class ChessBot {
    private static final String TOKEN = "NzcwMjIwMjY0NDgyMjc1MzM4.X5aZhQ.hrQFX3gh4X4QpaIVCR5vA6Slorg";
    private static String botID = "";
    public static void main(String[] args) throws Exception {
        commands.add(new HelpCommand());
        commands.add(new InfoCommand());
        commands.add(new SupportCommand());
        commands.add(new StartCommand());
        commands.add(new MoveCommand());
        JDA jda = JDABuilder.createDefault(TOKEN).build();
        jda.addEventListener(new ReadyEventListener(), new CommandEventListener());
        jda.getPresence().setActivity(Activity.watching(Utils.getPrefix() + "help"));
        botID = jda.getSelfUser().getId();
    }
    private static final List<Command> commands = new ArrayList<>();
    public static List<Command> getCommands() {
        return commands;
    }
    public static String getBotID() {
        return botID;
    }
}
