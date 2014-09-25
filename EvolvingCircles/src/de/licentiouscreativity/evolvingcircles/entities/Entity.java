package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public interface Entity {

    void update(final float delta);

    void draw(final Graphics2D g2d);

    void moveUp();
    void moveDown();
    void moveRight();
    void moveLeft();

    void stopMovingUp();
    void stopMovingDown();
    void stopMovingRight();
    void stopMovingLeft();

    boolean isInside(Entity entity);

    int getDirX();
    int getDirY();
    int getRadius();
    int getCenterX();
    int getCenterY();
}
