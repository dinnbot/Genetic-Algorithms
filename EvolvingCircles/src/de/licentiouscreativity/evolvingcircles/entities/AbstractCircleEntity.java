package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class AbstractCircleEntity implements Entity{

    protected int posX, posY, dirX, dirY;
    private int speed;
    private boolean movingUp, movingDown, movingLeft, movingRight, upInput, downInput, rightInput, leftInput;
    private final int height, width;
    private final Color color;

    public AbstractCircleEntity(final int posX, final int posY, final int width, final int height, final Color color) {
        this.posX = posX;
        this.posY = posY;
        this.height = height;
        this.width = width;
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
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(posX, posY, width, height);
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
}
