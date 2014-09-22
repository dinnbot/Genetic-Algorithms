package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.AICircleEntity;
import de.licentiouscreativity.evolvingcircles.entities.Entity;
import de.licentiouscreativity.evolvingcircles.entities.Individual;
import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class PopulationManager {

    final List<AICircleEntity> population;
    final Entity player;

    public PopulationManager() {
        population = new ArrayList<AICircleEntity>();
        population.add(new AICircleEntity(10, 10, 20, 20));

        player = PlayerCircleEntity.getInstance();
    }

    public void update(final float delta) {
        for (AICircleEntity entity : population) {
            entity.update(delta, player.getDirX, player.getDirY);
        }
    }

    public void draw(final Graphics2D g2d) {
        for (AICircleEntity entity : population) {
            entity.draw(g2d);
        }
    }
}
