package com.github.cyber_repocord.commands;

import com.github.cyber_repocord.helperclasses.Command;
import com.github.cyber_repocord.helperclasses.Game;
import com.github.cyber_repocord.helperclasses.Invite;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AcceptCommand implements Command {
    private static boolean enabled = true;
    private final static String[] aliases = {"ac"};
    @Override
    public void execute(GuildMessageReceivedEvent event, String[] args) throws IOException, IllegalArgumentException {
        EmbedBuilder builder = new EmbedBuilder();
        if (Utils.doesInviteExist(event.getAuthor().getId())) {
            Invite invite = Utils.getInvite(event.getAuthor().getId());
            if (!invite.getInvitee().equals(event.getAuthor().getId())) {
                builder.setTitle("No invite to accept");
                builder.setDescription("You have only an outgoing invite!");
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                builder.setColor(new Color(0xC80000));
                event.getChannel().sendMessage(builder.build()).queue();
            } else {
                builder.setTitle("Invite accepted!");
                builder.setDescription("You have accepted <@" + invite.getInviter() + ">'s invite.");
                builder.setAuthor(event.getAuthor().getAsTag(), null, event.getAuthor().getAvatarUrl());
                builder.setColor(new Color(0x0064C8));
                event.getChannel().sendMessage(builder.build()).queue();
                Utils.delInvite(invite);
                Game game;
                if (invite.isRandom()) {
                    game = new Game(invite.getInvitee(), invite.getInviter(), event.getChannel().getId());
                } else {
                    game = new Game(invite.getInvitee(), invite.getInviter(), event.getChannel().getId(), invite.isTurn());
                }
                game.sendAsEmbed(event);
            }
        } else {
            builder.setTitle("No invite to accept");
            builder.setDescription("You don't have pending invite!");
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
        return "Accept Command";
    }

    @Override
    public String getDesc() {
        return "Accepts invitation to game";
    }

    @Override
    public String getHelp() {
        // TODO: 30/10/2020 help message
        return "TO DO";
    }

    @Override
    public String getCommand() {
        return "accept";
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
        AcceptCommand.enabled = enabled;
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
