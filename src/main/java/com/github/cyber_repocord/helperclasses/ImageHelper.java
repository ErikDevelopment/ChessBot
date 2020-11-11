package com.github.cyber_repocord.helperclasses;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageHelper {
//    public static BufferedImage createImageFromBoard(int[][] board) throws IOException, IllegalArgumentException {
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[i].length; j++) {
//                switch (board[i][j]) {
//                    case 0:
//                        break;
//                    case 1:
//                        // something
//
//                        break;
//                    default:
//                        throw new IllegalArgumentException("Chess piece on " + i + ", " + j + " is unrecognized.");
//                }
//            }
//        }
//        return null;
//    }
    public static BufferedImage overlayImages(BufferedImage bgImage, BufferedImage fgImage, int x, int y) {
        if (fgImage.getHeight() > bgImage.getHeight() || fgImage.getWidth() > fgImage.getWidth()) {
            return null;
        }
        Graphics2D g = bgImage.createGraphics();
        g.drawImage(bgImage, 0, 0, null);
        g.drawImage(fgImage, x, y, null);
        g.dispose();
        return bgImage;
    }
    public static BufferedImage readImage(String fileLocation) throws IOException {
        return ImageIO.read(new File(fileLocation));
    }
    public static BufferedImage readImage(URL url) throws IOException {
        return ImageIO.read(url);
    }
    public static void writeImage(BufferedImage img, String fileLocation, String extension) throws IOException {
        ImageIO.write(img, extension, new File(fileLocation));
    }
}
