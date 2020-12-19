package com.github.cyber_repocord.helperclasses;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.util.List;

public interface Command {
    void execute(GuildMessageReceivedEvent event, String[] args) throws Exception;
    void execute(PrivateMessageReceivedEvent event, String[] args);
    String getName();
    String getDesc();
    String getHelp();
    String getCommand();
    boolean isEnabled();
    boolean isPrivate(boolean only);
    void setEnabled(boolean enabled);
    List<String> getAliases();
    String[] getAliasesAsArray();
}
