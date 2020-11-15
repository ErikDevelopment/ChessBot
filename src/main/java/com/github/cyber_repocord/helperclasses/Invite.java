package com.github.cyber_repocord.helperclasses;

import java.time.OffsetDateTime;

public class Invite {
    private final boolean turn;
    private final boolean random;
    private final String inviter;
    private final String invitee;
    private final OffsetDateTime time;
    public Invite(String inviter, String invitee, boolean turn, boolean random) {
        this.inviter = inviter;
        this.invitee = invitee;
        this.turn = turn;
        this.random = random;
        time = OffsetDateTime.now();
    }

    public String getInvitee() {
        return invitee;
    }

    public String getInviter() {
        return inviter;
    }
    public boolean isTurn() {
        return turn;
    }
    public boolean isRandom() {
        return random;
    }
    public OffsetDateTime getTime() {
        return time;
    }
}
