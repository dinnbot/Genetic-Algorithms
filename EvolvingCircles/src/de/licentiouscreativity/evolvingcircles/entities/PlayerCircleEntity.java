package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class PlayerCircleEntity extends AbstractCircleEntity{

    private static volatile PlayerCircleEntity instance;

    public static PlayerCircleEntity getInstance() {
        if (PlayerCircleEntity.instance == null) {
            createInstance();
        }
        return PlayerCircleEntity.instance;
    }

    private static synchronized void createInstance() {
        if (PlayerCircleEntity.instance == null) {
            PlayerCircleEntity.instance = new PlayerCircleEntity();
        }
    }

    private PlayerCircleEntity(){
        super(300, 300, 30, 30, Color.BLACK);
    }
}
