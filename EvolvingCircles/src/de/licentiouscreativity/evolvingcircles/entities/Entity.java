package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public interface Entity {

    public void update(final float delta);

    public void draw(final Graphics2D g2d);

    public void moveUp();
    public void moveDown();
    public void moveRight();
    public void moveLeft();

    public void stopMovingUp();
    public void stopMovingDown();
    public void stopMovingRight();
    public void stopMovingLeft();

    public int getPosX();
    public int getPosY();
    public int getDirX();
    public int getDirY();
    public int getCenterX();
    public int getCenterY();
    public int getRadius();
}
