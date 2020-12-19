package com.github.cyber_repocord.helperclasses;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Game {
    private byte[][] board;
    //         /\/\
    //         |  |
    //         y, x
    /*
    from 1 to 6 - white, from 7 to 12 - black
    0 - blank
    WHITE
    1 - pawn
    2 - knight
    3 - bishop
    4 - rook
    5 - queen
    6 - king
    BLACK
    7 - pawn
    8 - knight
    9 - bishop
    10 - rook
    11 - queen
    12 - king

    turn == true -> white
    turn == false -> black
             |
            \/
     */
    private boolean turn = true;

    private String player1;
    private String player2;


    private int movesCount = 0;
    private int movesCount2 = 0;

    private String channelId;

    private final long startTime;

    public Game(String player1, String player2, String channelId) {
        this(player1, player2, channelId, true, true);
    }
    public Game(String player1, String player2, String channelId, boolean turn) {
        this(player1, player2, channelId, false, turn);
    }
    private Game(String player1, String player2, String channelId, boolean random, boolean turn) {
        this.player1 = player1;
        this.player2 = player2;
        this.channelId = channelId;
        if (random) {
            if (new Random().nextBoolean()) {
                this.player1 = player1;
                this.player2 = player2;
            } else {
                this.player1 = player2;
                this.player2 = player1;
            }
        } else {
            if (turn) {
                this.player1 = player1;
                this.player2 = player2;
            } else {
                this.player1 = player2;
                this.player2 = player1;
            }
        }
        board = getDefaultBoard();
        startTime = System.currentTimeMillis();
    }

    // checks if player didn't make a move outside the board etc.
    private boolean checkIfMoveIsCorrect(Position fromTile, Position toTile) {
        return (!turn && ((board[fromTile.getX()][fromTile.getY()] < 7))) || (turn && ((board[fromTile.getX()][fromTile.getY()] == 0) || (board[fromTile.getX()][fromTile.getY()] > 6))) || ((fromTile.getX() < 0 || toTile.getX() < 0) || (fromTile.getY() < 0 || toTile.getY() < 0) || (fromTile.getX() > 7 || toTile.getX() > 7) || (fromTile.getY() > 7 || toTile.getY() > 7)) || !isNotOccupiedBySamePlayer(turn, toTile);
    }

    public boolean makeMove(Position fromTile, Position toTile) throws IllegalArgumentException {
        if (!checkIfMoveIsCorrect(fromTile, toTile)) throw new IllegalArgumentException();

        int moveX = fromTile.getX() - toTile.getX(), moveY = fromTile.getY() - toTile.getY();

        boolean allowed = false;

        switch (board[fromTile.getX()][fromTile.getY()]) {
            case 1:
                if (moveX == 0 && moveY == 1) {
                    allowed = true;
                } else if (moveX == 0 && moveY == 2 && fromTile.getY() == 1) {
                    allowed = true;
                } else if (moveX == 1 || moveX == -1 && moveY == 1) {
                    allowed = true;
                }
                break;
            case 7:
                if (moveX == 0 && moveY == -1) {
                    allowed = true;
                } else if (moveX == 0 && moveY == -2 && fromTile.getY() == 6) {
                    allowed = true;
                } else if (moveX == 1 || moveX == -1 && moveY == -1) {
                    allowed = true;
                }
                break;
            case 2:
            case 8:
                break;
            case 3:
            case 9:
                break;
            case 4:
            case 10:
                break;
            case 5:
            case 11:
                break;
            case 6:
            case 12:
                break;
            default:
                throw new IllegalArgumentException();
        }
        if (allowed) move(fromTile, toTile);
        else System.out.println("c");
        return allowed;
    }

    private void move(Position fromTile, Position toTile) {
        board[toTile.getY()][toTile.getX()] = board[fromTile.getY()][fromTile.getX()];
        board[fromTile.getY()][fromTile.getX()] = 0;

        if (turn) {
            movesCount++;
        } else {
            movesCount2++;
        }
        turn = !turn;
    }

    private boolean isNotOccupiedBySamePlayer(boolean turn, Position pos) {
        if (turn) {
            return board[pos.getX()][pos.getY()] <= 6 && board[pos.getX()][pos.getY()] >= 0;
        }
        else {
            return board[pos.getX()][pos.getY()] >= 7 && board[pos.getX()][pos.getY()] <= 12;
        }
    }

    public static byte[][] getDefaultBoard() {
        byte[][] board = new byte[8][8];

        board[0][0] = 10;
        board[0][1] = 8;
        board[0][2] = 9;
        board[0][3] = 11;
        board[0][4] = 12;
        board[0][5] = 9;
        board[0][6] = 8;
        board[0][7] = 10;

        board[1][0] = 7;
        board[1][1] = 7;
        board[1][2] = 7;
        board[1][3] = 7;
        board[1][4] = 7;
        board[1][5] = 7;
        board[1][6] = 7;
        board[1][7] = 7;

        board[6][0] = 1;
        board[6][1] = 1;
        board[6][2] = 1;
        board[6][3] = 1;
        board[6][4] = 1;
        board[6][5] = 1;
        board[6][6] = 1;
        board[6][7] = 1;

        board[7][0] = 4;
        board[7][1] = 2;
        board[7][2] = 3;
        board[7][3] = 5;
        board[7][4] = 6;
        board[7][5] = 3;
        board[7][6] = 2;
        board[7][7] = 4;

        return board;
    }

    public synchronized File getAsImage() throws IOException, IllegalArgumentException {
        return ImageHelper.getImageFromBoard(board, Utils.getTempFilePath());
    }

    public void sendAsEmbed(GuildMessageReceivedEvent event) throws IOException, IllegalArgumentException {
        final EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Game");
        builder.addField(new MessageEmbed.Field("Players:", "White: <@" + player1 + ">\nBlack: <@" + player2 + ">", true));
        builder.addField(new MessageEmbed.Field("Moves:", "White: " + movesCount + "\nBlack: " + movesCount2, true));
        long millis = System.currentTimeMillis() - startTime;
        long minutes = millis / 360000;
        long seconds = (millis / 1000) - (minutes * 60);
        String time = minutes + "m, " + seconds + "s";
        builder.addField(new MessageEmbed.Field("Time:", time, true));
        builder.setImage("attachment://game.png");
        builder.setColor(new Color(0x0064C8));
        event.getChannel().sendMessage(builder.build()).addFile(getAsImage(), "game.png").queue();
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public boolean makeMove(String args[]) throws IllegalArgumentException {
        if (args.length <= 2) {
            throw new IllegalArgumentException();
        }
        if (args[1].length() != 2 || args[2].length() != 2) {
            throw new IllegalArgumentException();
        }
        Position pos1 = new Position(getNumFromNum(args[1].toCharArray()[0]) - 1, getNumFromLetter(args[1].toCharArray()[1]) - 1);
        Position pos2 = new Position(getNumFromNum(args[2].toCharArray()[0]) - 1, getNumFromLetter(args[2].toCharArray()[1]) - 1);
        return makeMove(pos1, pos2);
    }
    private int getNumFromLetter(char a) throws IllegalArgumentException {
        int num;
        switch (a) {
            case 'a':
                num = 1;
                break;
            case 'b':
                num = 2;
                break;
            case 'c':
                num = 3;
                break;
            case 'd':
                num = 4;
                break;
            case 'e':
                num = 5;
                break;
            case 'f':
                num = 6;
                break;
            case 'g':
                num = 7;
                break;
            case 'h':
                num = 8;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return num;
    }
    private int getNumFromNum(char a) throws IllegalArgumentException{
        int num;
        switch (a) {
            case '1':
                num = 1;
                break;
            case '2':
                num = 2;
                break;
            case '3':
                num = 3;
                break;
            case '4':
                num = 4;
                break;
            case '5':
                num = 5;
                break;
            case '6':
                num = 6;
                break;
            case '7':
                num = 7;
                break;
            case '8':
                num = 8;
                break;
            default:
                throw new IllegalArgumentException();

        }
        return num;
    }
    public boolean isTurn() {
        return turn;
    }
}
