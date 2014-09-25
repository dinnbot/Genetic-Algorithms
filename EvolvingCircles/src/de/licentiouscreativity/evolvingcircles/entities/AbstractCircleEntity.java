package de.licentiouscreativity.evolvingcircles.entities;

import de.licentiouscreativity.evolvingcircles.Window;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class AbstractCircleEntity implements Entity{

    private static int SPEED = 1;

    protected int posX, posY, dirX, dirY;
    private boolean hasUpInput, hasDownInput, hasRightInput, hasLeftInput;
    protected int radius;
    private final Color color;

    public AbstractCircleEntity(final int posX, final int posY, final int radius, final Color color) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void update(final float delta) {
        calculateNewPosition(delta);
        keepEntityInCanvas();
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setColor(color);
        g2d.fillOval(posX, posY, radius*2, radius*2);
    }

    @Override
    public void moveUp() {
        hasUpInput = true;
        setDirY(-SPEED);
    }

    @Override
    public void moveDown() {
        hasDownInput = true;
        setDirY(SPEED);
    }

    @Override
    public void moveRight() {
        hasRightInput = true;
        setDirX(SPEED);
    }

    @Override
    public void moveLeft() {
        hasLeftInput = true;
        setDirX(-SPEED);
    }

    @Override
    public void stopMovingUp() {
        hasUpInput = false;
        if (hasDownInput)
            moveDown();
        else
            stopMovingY();
    }

    @Override
    public void stopMovingDown() {
        hasDownInput = false;
        if (hasUpInput)
            moveUp();
        else
            stopMovingY();
    }

    @Override
    public void stopMovingRight() {
        hasRightInput = false;
        if (hasLeftInput)
            moveLeft();
        else
            stopMovingX();
    }

    @Override
    public void stopMovingLeft() {
        hasLeftInput = false;
        if (hasRightInput)
            moveRight();
        else
            stopMovingX();
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
    public int getRadius() {
        return radius;
    }

    @Override
    public int getCenterX() {
        return posX+radius;
    }

    @Override
    public int getCenterY() {
        return posY+radius;
    }

    public boolean isInside(Entity entity) {
        return getDistanceTo(entity.getCenterX(), entity.getCenterY()) - entity.getRadius() <= radius;
    }

    private void setDirX(final int dirX) {
        this.dirX = dirX;
    }

    private void setDirY(final int dirY) {
        this.dirY = dirY;
    }

    private void calculateNewPosition(final float delta) {
        posX += dirX*delta;
        posY += dirY*delta;
    }

    private void keepEntityInCanvas() {
        if (posX < Window.SCREEN_LEFT_EDGE) setPosX(Window.SCREEN_LEFT_EDGE);
        if (getRightPosX() > Window.SCREEN_RIGHT_EDGE) setPosX(Window.SCREEN_RIGHT_EDGE-radius*2);
        if (posY < Window.SCREEN_TOP_EDGE) setPosY(Window.SCREEN_TOP_EDGE);
        if (getBottomPosY() > Window.SCREEN_BOTTOM_EDGE) setPosY(Window.SCREEN_BOTTOM_EDGE-radius*2);
    }

    private int getRightPosX() { return posX + (radius * 2); }

    private int getBottomPosY() { return posY + (radius * 2); }

    private void setPosX(final int posX) { this.posX = posX; }

    private void setPosY(final int posY) { this.posY = posY; }

    private double getDistanceTo(final int centerX, final int centerY) {
        return Math.sqrt(Math.pow(centerX - getCenterX(), 2) + Math.pow(centerY - getCenterY(), 2));
    }

    private void stopMovingX() { setDirX(0); }

    private void stopMovingY() { setDirY(0); }
}
