package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.AICircleEntity;
import de.licentiouscreativity.evolvingcircles.entities.Entity;
import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.*;
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

    private final int populationSize = 10;

    private final Evolution evolution;
    private final List<AICircleEntity> population;
    private final Entity player;

    private final List<AICircleEntity> deadAIs;
    List<AICircleEntity> newPopulation;
    Map<AICircleEntity, AICircleEntity> crossed;
    Map<AICircleEntity, AICircleEntity> crossedDelete;

    private PopulationManager() {
        evolution = Evolution.getInstance();
        population = new ArrayList<AICircleEntity>();

        deadAIs = new ArrayList<AICircleEntity>();
        newPopulation = new ArrayList<AICircleEntity>();
        crossed = new HashMap<AICircleEntity, AICircleEntity>();
        crossedDelete = new HashMap<AICircleEntity, AICircleEntity>();

        player = PlayerCircleEntity.getInstance();

        initPopulation();
    }

    private void initPopulation() {
        List<String> chromosomes = evolution.initPopulation(populationSize);
        Random random = new Random();

        for (String chromosome : chromosomes) {
            population.add(new AICircleEntity(random.nextInt(800), random.nextInt(400), 10, chromosome));
        }
    }

    public void update(final float delta) {
        int pDirX = player.getDirX();
        int pDirY = player.getDirY();
        for (AICircleEntity entity : population) {
            entity.update(delta, pDirX, pDirY);
        }


        for (AICircleEntity entity1 : population) {
            if (!crossed.containsKey(entity1) && !crossed.containsValue(entity1)) {
                for (AICircleEntity entity2 : population) {
                    if (entity1 != entity2 && !crossed.containsKey(entity2) && !crossed.containsValue(entity2)) {
                        if (entity1.isInside(entity2)) {
                            crossed.put(entity1, entity2);
                            newPopulation.add(new AICircleEntity(entity1.getPosX(), entity1.getPosY(), entity1.getRadius(), evolution.cross(entity1, entity2)));
                        }
                    }
                }
            }
        }
        population.addAll(newPopulation);
        newPopulation.clear();

        for (AICircleEntity entity1 : crossed.keySet()) {
            AICircleEntity entity2 = crossed.get(entity1);
            if (!entity1.isInside(entity2)) crossedDelete.put(entity1, entity2);
        }
        for (AICircleEntity entity1 : crossedDelete.keySet()) {
            AICircleEntity entity2 = crossedDelete.get(entity1);
            crossed.remove(entity1, entity2);
        }
        crossedDelete.clear();

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
