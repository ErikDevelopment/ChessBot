package com.github.cyber_repocord.helperclasses;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

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
    private String channelId;
    private long moveTimeStamp = System.currentTimeMillis();
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
    }
    public boolean makeMove(boolean player, Position fromTile, Position toTile) {
        // check if every value is correct
        if ((!player && ((board[fromTile.getX()][fromTile.getY()] < 7))) || (player && ((board[fromTile.getX()][fromTile.getY()] == 0) || (board[fromTile.getX()][fromTile.getY()] > 6))) || ((fromTile.getX() < 0 || toTile.getX() < 0) || (fromTile.getY() < 0 || toTile.getY() < 0) || (fromTile.getX() > 7 || toTile.getX() > 7) || (fromTile.getY() > 7 || toTile.getY() > 7)) || !isNotOccupiedBySamePlayer(turn, toTile)) return false;
        int moveX = fromTile.getX() - toTile.getX(), moveY = fromTile.getY() - toTile.getY();
        switch (board[fromTile.getX()][fromTile.getY()]) {
            case 1:
                if (moveX == 1 && moveY == 0) {
                    return move(fromTile, toTile);
                } else if (moveX == 2 && moveY == 0 && fromTile.getX() == 1) {
                    return move(fromTile, toTile);
                }
                break;
            case 7:
                if (moveX == -1 && moveY == 0) {
                    return move(fromTile, toTile);
                } else if (moveX == -2 && moveY == 0 && fromTile.getX() == 6) {
                    return move(fromTile, toTile);
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
                return false;
        }
        return true;
    }
    private boolean move(Position fromTile, Position toTile) {
        board[toTile.getX()][toTile.getY()] = board[fromTile.getX()][fromTile.getY()];
        board[fromTile.getX()][fromTile.getY()] = 0;
        // always returns true (don't remove)
        return true;
    }
    public byte[][] getBoard() {
        return board;
    }
    public void setBoard(byte[][] board) {
        if (board.length != 8) return;
        if (board[7].length != 8) return;
        this.board = board;
    }
    private boolean isNotOccupiedBySamePlayer(boolean turn, Position pos) {
        if (turn) {
            return board[pos.getX()][pos.getY()] <= 6 && board[pos.getX()][pos.getY()] >= 0;
        }
        else {
            return board[pos.getX()][pos.getY()] >= 7 && board[pos.getX()][pos.getY()] <= 12;
        }
    }
    public String getGameAsText() {
        return getGameAsText(board);
    }
    public static String getGameAsText(byte[][] board) {
        if (board.length != 8) return "";
        if (board[7].length != 8) return "";
        StringBuilder game = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0:
                        if ((i + (j % 2)) % 2 == 0) game.append(":white_large_square:");
                        else game.append(":black_large_square:");
                        break;
                    case 1:
                        game.append(":grinning:");
                        break;
                    case 2:
                        game.append(":smiley:");
                        break;
                    case 3:
                        game.append(":smile:");
                        break;
                    case 4:
                        game.append(":grin:");
                        break;
                    case 5:
                        game.append(":laughing:");
                        break;
                    case 6:
                        game.append(":sweat_smile:");
                        break;
                    case 7:
                        game.append(":joy:");
                        break;
                    case 8:
                        game.append(":rofl:");
                        break;
                    case 9:
                        game.append(":relaxed:");
                        break;
                    case 10:
                        game.append(":blush:");
                        break;
                    case 11:
                        game.append(":innocent:");
                        break;
                    case 12:
                        game.append(":slight_smile:");
                        break;
                }
            }
            game.append("\n");
        }
        return game.toString();
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
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Game");
        builder.setImage("attachment://game.png");
        
        event.getChannel().sendMessage(builder.build()).addFile(getAsImage(), "game.png").queue();
    }
}
