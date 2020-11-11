package com.github.cyber_repocord.helperclasses;


import java.util.HashMap;

public class Utils {
    private Utils() {}
    private static final String PREFIX = "ch!";
//    public static Position getImgPosFromBoardPos(int x, int y) throws IllegalArgumentException {
//        if (x > 9 || y > 9) throw new IllegalArgumentException("Incorrect x or y value.");
//        return new Position((x * 98) + 100, (y * 98));
//    }
    public static boolean isBeta() {
        return true;
    }
    public static String getBetaChannel() {
        return "770219287414571068";
    }
    public static String getPrefix() {
        return PREFIX;
    }
    private static HashMap<String, Game> boards = new HashMap<>();
    public static boolean getIfGameExists(String channelID) {
        return boards.containsKey(channelID);
    }
    public static Game getGame(String channelID) {
        return boards.get(channelID);
    }
    public static void setGame(String channelID, Game game) {
        boards.put(channelID, game);
    }
}
