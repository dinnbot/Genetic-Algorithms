package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.AICircleEntity;
import de.licentiouscreativity.evolvingcircles.entities.Entity;
import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class PopulationManager {

    private static volatile PopulationManager instance;

    public static PopulationManager getInstance() {
        if (PopulationManager.instance == null) {
            createInstance();
        }
        return PopulationManager.instance;
    }

    private static synchronized void createInstance() {
        if (PopulationManager.instance == null) {
            PopulationManager.instance = new PopulationManager();
        }
    }

    final List<AICircleEntity> population;
    final Entity player;

    final List<AICircleEntity> deadAIs;

    private PopulationManager() {
        population = new ArrayList<AICircleEntity>();
        deadAIs = new ArrayList<AICircleEntity>();

        player = PlayerCircleEntity.getInstance();
    }

    public void update(final float delta) {
        for (AICircleEntity entity : population) {
            entity.update(delta, player.getDirX(), player.getDirY());
        }

        List<AICircleEntity> crossed = new ArrayList<AICircleEntity>();
        for (int i = 0; i < population.size(); i++) {
            for (int j = 0; j < population.size(); j++) {
                //if (!crossed.contains()&& population.get(i).isInside(notCrossed.get(j))) TODO paaren
                //add crossed
            }
        }

        population.removeAll(deadAIs);
        deadAIs.clear();
    }

    public void draw(final Graphics2D g2d) {
        for (AICircleEntity entity : population) {
            entity.draw(g2d);
        }
    }

    public void killIndividual(Entity player) {
        for (AICircleEntity entity : population) {
            if (entity.isInside(player)) {
                deadAIs.add(entity);
            }
        }
    }
}
