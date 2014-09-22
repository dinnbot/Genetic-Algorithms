package de.licentiouscreativity.evolvingcircles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class Window extends JFrame{

    public final static int SCREEN_WIDTH = 800;
    public final static int SCREEN_HEIGHT = 400;
    public final static int SCREEN_TOP_EDGE = 24;

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
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(new Input());

        world = World.getInstance();
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage bufferedImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        super.paint(g2d);

        world.live(g2d);

        Graphics2D g2dComponent = (Graphics2D) g;
        g2dComponent.drawImage(bufferedImage, null, 0, 0);
    }
}
