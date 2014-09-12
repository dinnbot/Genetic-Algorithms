package de.licentiouscreativity.evolvingcircles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class Window extends JFrame{

    private static volatile Window instance;

    public static Window getInstance() {
        if (Window.instance == null) {
            createInstance();
        }
        return Window.instance;
    }

    private static synchronized void createInstance() {
        Window.instance = new Window();
    }

    private final World world;

    private Window() {
        setSize(800, 480);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        world = World.getInstance();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();

        world.live(g2d);

        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
    }
}
