package com.github.cyber_repocord.helperclasses;

import java.util.Random;

public class Game {
    private int[][] board;
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
            /|
            \/
     */
    private boolean turn;
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
            this.turn = new Random().nextBoolean();
        } else {
            this.turn = turn;
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
    public int[][] getBoard() {
        return board;
    }
    public void setBoard(int[][] board) {
        if (board.length != 8) return;
        if (board[7].length != 8) return;
        this.board = board;
    }
    private boolean isNotOccupiedBySamePlayer(boolean turn, Position pos) {
        if (turn) { if (board[pos.getX()][pos.getY()] > 6 || board[pos.getX()][pos.getY()] < 0) return false; else return true; }
        else { if (board[pos.getX()][pos.getY()] < 7 || board[pos.getX()][pos.getY()] > 12) return false; else return true; }
    }
    public String getGameAsText() {
        String game = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0:
                        if (i + (j % 2) % 2 == 1) game += "a";
                        else game += "b";
                        break;
                }
            }
            game += "\n";
        }
        return game;
    }
    public static String getGameAsText(int[][] board) {
        if (board.length != 8) return "";
        if (board[7].length != 8) return "";
        String game = "";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                switch (board[i][j]) {
                    case 0:
                        if ((i + (j % 2)) % 2 == 0) game += ":white_large_square:";
                        else game += ":black_large_square:";
                        break;
                    case 1:
                        game += ":grinning:";
                        break;
                    case 2:
                        game += ":smiley:";
                        break;
                    case 3:
                        game += ":smile:";
                        break;
                    case 4:
                        game += ":grin:";
                        break;
                    case 5:
                        game += ":laughing:";
                        break;
                    case 6:
                        game += ":sweat_smile:";
                        break;
                    case 7:
                        game += ":joy:";
                        break;
                    case 8:
                        game += ":rofl:";
                        break;
                    case 9:
                        game += ":relaxed:";
                        break;
                    case 10:
                        game += ":blush:";
                        break;
                    case 11:
                        game += ":innocent:";
                        break;
                    case 12:
                        game += ":slight_smile:";
                        break;
                }
            }
            game += "\n";
        }
        return game;
    }
    public static int[][] getDefaultBoard() {
        int[][] board = new int[8][8];

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
}
