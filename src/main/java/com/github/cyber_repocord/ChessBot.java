package com.github.cyber_repocord;

import com.github.cyber_repocord.events.CommandEventListener;
import com.github.cyber_repocord.events.ReadyEventListener;
import com.github.cyber_repocord.helperclasses.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class ChessBot {
    private static byte status;
    private static JDABuilder shardBuilder;
    private static JDA jda;
    public static void main(String[] args) throws Exception {


        shardBuilder = JDABuilder.createDefault(Utils.getToken());
        shardBuilder.addEventListeners(new CommandEventListener(), new ReadyEventListener());
        for (int i = 0; i < getShardsCount(); i++)
        {
            shardBuilder.useSharding(i, getShardsCount())
                    .build();
        }
        shardBuilder.setActivity(Activity.watching(Utils.getPrefix() + "help"));
        jda = shardBuilder.build();
        startChangingStatuses(jda, (byte) 0);
    }
    public static JDA getJDA() {
        return jda;
    }
    public static int getShardsCount() {
        return 0;
    }
    private static void startChangingStatuses(JDA jda, byte status) {
        ChessBot.status = status;
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                switch (getStatus()) {
                    case 0:
                        jda.getPresence().setActivity(Activity.watching(jda.getGuilds().toArray().length + " servers"));
                        break;
                    case 1:
                        jda.getPresence().setActivity(Activity.watching(Utils.getPrefix() + "help"));
                        break;
                    case 2:
                        long durationInSec = Duration.between(Utils.getRestartDate(), OffsetDateTime.now()).getSeconds();
                        jda.getPresence().setActivity(Activity.watching("restarted " + durationInSec / 3600 + "h " + durationInSec % 3600 / 60 + "m " + durationInSec % 60 + "s ago"));
                        break;
                    case 3:
                        jda.getPresence().setActivity(Activity.playing("-> " + Utils.getPlayedGamesCount() + " games right now"));
                        break;
                }

            }
        }, 3000L, 3000L);
    }
    private static byte getStatus() {
        byte statusBefore = status;
        if (status >= 3) {
            status = 0;
        } else {
            status++;
        }
        return statusBefore;
    }
}
