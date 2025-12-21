package org.multithreading.recorders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class SimpleScreenshot implements Runnable{

    private final String pathToFolder = "/home//shamim//Pictures//ScreenShotsFromProgram";

    @Override
    public void run() {
        for(int i=0; i<10000; i++) {

            try {
                Thread.sleep(1000);
                // Create Robot instance
                Robot robot = new Robot();
                // Get screen size
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Rectangle captureRect = new Rectangle(screenSize);
                // Capture the screen
                BufferedImage image = robot.createScreenCapture(captureRect);
                // Save the image
                ImageIO.write(image, "png", new File(pathToFolder+"/screenshot" + i + ".png"));
                System.out.println("Screenshot saved!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
