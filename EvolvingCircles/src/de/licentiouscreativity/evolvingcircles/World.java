package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.*;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class World {

    private static volatile World instance;

    private final PlayerCircleEntity player;
    private final Population population;

    public static World getInstance() {
        if (World.instance == null) {
            createInstance();
        }
        return World.instance;
    }

    private static synchronized void createInstance() {
        if (World.instance == null) {
            World.instance = new World();
        }
    }

    private World() {
        player = PlayerCircleEntity.getInstance();
        population = Population.getInstance();
    }

    public void live(final Graphics2D g2d) {
        update(1); //TODO real delta time
        draw(g2d);
    }

    private void update(final float delta) {
        player.update(delta);
        population.update(delta);
    }

    private void draw(final Graphics2D g2d) {
        player.draw(g2d);
        population.draw(g2d);
    }
}
