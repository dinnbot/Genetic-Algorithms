package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class AbstractCircleEntity {

    private int posX, posY, dirX, dirY;
    private final int height, width;
    private final Color color;

    public AbstractCircleEntity(final int posX, final int posY, final int width, final int height, final Color color) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public void update(final float delta) {
        posX += dirX*delta;
        posY += dirY*delta;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(posX, posY, width, height);
    }
}
