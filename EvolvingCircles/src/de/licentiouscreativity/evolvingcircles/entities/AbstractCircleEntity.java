package de.licentiouscreativity.evolvingcircles.entities;

import de.licentiouscreativity.evolvingcircles.*;
import de.licentiouscreativity.evolvingcircles.Window;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class AbstractCircleEntity implements Entity{

    protected int posX, posY, dirX, dirY;
    private int speed;
    private boolean movingUp, movingDown, movingLeft, movingRight, upInput, downInput, rightInput, leftInput;
    protected final int radius;
    private final Color color;

    public AbstractCircleEntity(final int posX, final int posY, final int radius, final Color color) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = color;
        speed = 1;
        movingUp = false;
        movingDown = false;
        movingRight = false;
        movingLeft = false;
    }

    public void update(final float delta) {
        posX += dirX*delta;
        posY += dirY*delta;

        if (posX < 0) posX = 0;
        if (posX+radius*2 > Window.SCREEN_WIDTH) posX = Window.SCREEN_WIDTH-radius*2;
        if (posY < Window.SCREEN_TOP_EDGE) posY = Window.SCREEN_TOP_EDGE;
        if (posY+radius*2 > Window.SCREEN_HEIGHT) posY = Window.SCREEN_HEIGHT-radius*2;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(posX, posY, radius*2, radius*2);
    }

    @Override
    public void moveUp() {
        upInput = true;
        movingUp = true;
        movingDown = false;
        dirY = -speed;
    }

    @Override
    public void moveDown() {
        downInput = true;
        movingDown = true;
        movingUp = false;
        dirY = speed;
    }

    @Override
    public void moveRight() {
        rightInput = true;
        movingRight = true;
        movingLeft = false;
        dirX = speed;
    }

    @Override
    public void moveLeft() {
        leftInput = true;
        movingLeft = true;
        movingRight = false;
        dirX = -speed;
    }

    @Override
    public void stopMovingUp() {
        upInput = false;
        movingUp = false;
        if (downInput) {
            dirY = -speed;
        } else {
            dirY = 0;
        }
    }

    @Override
    public void stopMovingDown() {
        downInput = false;
        movingDown = false;
        if (upInput) {
            dirY = speed;
        } else {
            dirY = 0;
        }
    }

    @Override
    public void stopMovingRight() {
        rightInput = false;
        movingRight = false;
        if (leftInput) {
            dirX = -speed;
        } else {
            dirX = 0;
        }
    }

    @Override
    public void stopMovingLeft() {
        leftInput = false;
        movingLeft = false;
        if (rightInput) {
            dirX = speed;
        } else {
            dirX = 0;
        }
    }

    @Override
    public int getPosX() {
        return posX;
    }

    @Override
    public int getPosY() {
        return posY;
    }

    @Override
    public int getDirX() {
        return dirX;
    }

    @Override
    public int getDirY() {
        return dirY;
    }

    @Override
    public int getCenterX() {
        return posX+radius;
    }

    @Override
    public int getCenterY() {
        return posY+radius;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    public double getDistanceTo(final int centerX, final int centerY) {
        return Math.sqrt(Math.pow(centerX - getCenterX(), 2) + Math.pow(centerY - getCenterY(), 2));
    }

    public boolean isInside(Entity entity) {
        return getDistanceTo(entity.getCenterX(), entity.getCenterY()) - entity.getRadius() <= radius;
    }
}
