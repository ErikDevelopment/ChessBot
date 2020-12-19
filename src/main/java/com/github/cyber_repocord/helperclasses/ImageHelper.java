package com.github.cyber_repocord.helperclasses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageHelper {
    public static synchronized File getImageFromBoard(byte[][] board, String path) throws IOException, IllegalArgumentException {
        saveImageFromBoard(board, path);
        return new File(path);
    }
    public static synchronized void saveImageFromBoard(byte[][] board, String fileName) throws IOException, IllegalArgumentException {
        writeImage(createImageFromBoard(board), fileName, "PNG");
    }
    public static BufferedImage createImageFromBoard(byte[][] board) throws IOException, IllegalArgumentException {
        BufferedImage boardImage = readImage("/pictures/board.png");
        BufferedImage[] imgs = {readImage("/pictures/pawn.png"), readImage("/pictures/horse.png"), readImage("/pictures/bishop.png"), readImage("/pictures/rook.png"), readImage("/pictures/queen.png"), readImage("/pictures/king.png") /* black */, readImage("/pictures/pawn-1.png/"), readImage("/pictures/horse-1.png"), readImage("/pictures/bishop-1.png"), readImage("/pictures/rook-1.png"), readImage("/pictures/queen-1.png"), readImage("/pictures/king-1.png")};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 0 || (board[i][j] <= 12 && board[i][j] >= 1)) {
                    if (board[i][j] != 0) {
                        Position pos = getPos(i, j);
                        overlayImages(boardImage, imgs[board[i][j] - 1], pos.getX(), pos.getY(), 60, 60);
                    }
                } else throw new IllegalArgumentException("Chess piece on " + i + ", " + j + " is unrecognized. (" + board[i][j] + ")");
            }
        }
        return boardImage;
    }
    public static void overlayImages(BufferedImage bgImage, BufferedImage fgImage, int x, int y, int sizeX, int sizeY) {
        Graphics2D g = bgImage.createGraphics();
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(fgImage, x, y, sizeX, sizeY, null);
        g.dispose();
    }
    public static BufferedImage readImage(String fileLocation) throws IOException {
        return ImageIO.read(ImageHelper.class.getResourceAsStream(fileLocation));
    }
    public static synchronized void writeImage(BufferedImage img, String fileLocation, String extension) throws IOException {
        ImageIO.write(img, extension, new File(fileLocation));
    }
    public static Position getPos(int x, int y) {
        return new Position(x * 100 + 120, y * 100 + 120);
    }
}
