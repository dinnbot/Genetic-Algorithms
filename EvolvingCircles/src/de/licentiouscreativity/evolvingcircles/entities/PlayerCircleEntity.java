package de.licentiouscreativity.evolvingcircles.entities;

import de.licentiouscreativity.evolvingcircles.PopulationManager;

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

    private PopulationManager populationManager;

    private PlayerCircleEntity(){
        super(300, 300, 30, Color.BLACK);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (populationManager == null) populationManager = PopulationManager.getInstance(); //TODO wohin?
        populationManager.killIndividual(this);
    }
}
