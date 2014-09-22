package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.Entity;
import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class Input implements KeyListener{

    private final Entity entity;

    public Input() {
        entity = PlayerCircleEntity.getInstance();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                entity.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                entity.moveDown();
                break;
            case KeyEvent.VK_RIGHT:
                entity.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                entity.moveLeft();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                entity.stopMovingUp();
                break;
            case KeyEvent.VK_DOWN:
                entity.stopMovingDown();
                break;
            case KeyEvent.VK_RIGHT:
                entity.stopMovingRight();
                break;
            case KeyEvent.VK_LEFT:
                entity.stopMovingLeft();
                break;
        }
    }
}
