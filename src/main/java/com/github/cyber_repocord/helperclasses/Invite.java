package com.github.cyber_repocord.helperclasses;

public class Invite {
    private final boolean turn;
    private final boolean random;
    private final String inviter;
    private final String invitee;
    private final long time;
    public Invite(String inviter, String invitee, boolean turn, boolean random) {
        this.inviter = inviter;
        this.invitee = invitee;
        this.turn = turn;
        this.random = random;
        time = System.currentTimeMillis();
    }
    public final String getInvitee() {
        return invitee;
    }
    public final String getInviter() {
        return inviter;
    }
    public final boolean isTurn() {
        return turn;
    }
    public final boolean isRandom() {
        return random;
    }
    public final long getTime() {
        return time;
    }
}
