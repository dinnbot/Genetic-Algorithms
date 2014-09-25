package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.AICircleIndividualEntity;
import de.licentiouscreativity.evolvingcircles.entities.Entity;
import de.licentiouscreativity.evolvingcircles.entities.IndividualEntity;
import de.licentiouscreativity.evolvingcircles.entities.PlayerCircleEntity;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class Population {

    private static final int STOP_REPRODUCING_POPULATION_SIZE = 20;
    private static final int INITIAL_POPULATION_SIZE = 10;
    private static volatile Population instance;
    private final Evolution evolution;

    private final Entity player;
    private final Random random;
    private final List<IndividualEntity> population;
    private Map<IndividualEntity, IndividualEntity> reproducingPairs;
    private final List<IndividualEntity> deadAIs;
    private List<IndividualEntity> newPopulation;

    public static Population getInstance() {
        if (Population.instance == null) {
            createInstance();
        }
        return Population.instance;
    }

    private static synchronized void createInstance() {
        if (Population.instance == null) {
            Population.instance = new Population();
        }
    }

    private Population() {
        evolution = Evolution.getInstance();
        player = PlayerCircleEntity.getInstance();
        random = new Random();
        population = new ArrayList<IndividualEntity>();
        reproducingPairs = new HashMap<IndividualEntity, IndividualEntity>();
        deadAIs = new ArrayList<IndividualEntity>();
        newPopulation = new ArrayList<IndividualEntity>();

        initPopulation();
    }

    public void update(final float delta) {
        updateAllIndividualEntities(delta);
        reproduceOverlappingIndividuals();

        population.removeAll(deadAIs);
        deadAIs.clear();
    }

    public void draw(final Graphics2D g2d) {
        for (Entity entity : population) {
            entity.draw(g2d);
        }
        g2d.setColor(Color.BLUE);
        g2d.drawString(String.valueOf(population.size()), 100, 100);
    }

    public void killIndividuals() {
        for (IndividualEntity individualEntity : population) {
            if (individualEntity.isInside(player)) {
                deadAIs.add(individualEntity);
            }
        }
    }

    private void initPopulation() {
        List<String> chromosomes = evolution.createChromosomesForInitialPopulation(INITIAL_POPULATION_SIZE);

        for (String chromosome : chromosomes) {
            population.add(createIndividualEntityWithRandomPosition(chromosome));
        }
    }

    private IndividualEntity createIndividualEntityWithRandomPosition(final String chromosome) {
        return new AICircleIndividualEntity(getRandomPosX(), getRandomPosY(), chromosome);
    }

    private int getRandomPosX() {
        return random.nextInt(Window.SCREEN_WIDTH);
    }

    private int getRandomPosY() {
        return random.nextInt(Window.SCREEN_HEIGHT);
    }

    private void updateAllIndividualEntities(final float delta) {
        for (IndividualEntity individualEntity : population) {
            individualEntity.update(delta);
        }
    }

    private void reproduceOverlappingIndividuals() {
        refreshReproducingPairs();

        if (isPopulationSmallEnoughToReproduce()) {
            for (IndividualEntity individualEntity1 : population) {
                if (!isIndividualEntityReproducing(individualEntity1)) {
                    for (IndividualEntity individualEntity2 : population) {
                        if (individualEntity1 != individualEntity2 && !isIndividualEntityReproducing(individualEntity2)) {
                            if (individualEntity1.isInside(individualEntity2)) {
                                reproducingPairs.put(individualEntity1, individualEntity2);
                                newPopulation.add(createIndividualEntityWithRandomPosition(evolution.reproduceToNewChromosome(individualEntity1, individualEntity2)));
                            }
                        }
                    }
                }
            }
            population.addAll(newPopulation);
            newPopulation.clear();
        }
    }

    private void refreshReproducingPairs() {
        Map<IndividualEntity, IndividualEntity> nonexistentReproducingPairs = getNonexistentReproducingPairs();

        for (IndividualEntity individualEntity1 : nonexistentReproducingPairs.keySet()) {
            IndividualEntity individualEntity2 = nonexistentReproducingPairs.get(individualEntity1);
            reproducingPairs.remove(individualEntity1, individualEntity2);
        }
    }

    private Map<IndividualEntity, IndividualEntity> getNonexistentReproducingPairs() {
        Map<IndividualEntity, IndividualEntity> nonexistentReproducingPairs = new HashMap<IndividualEntity, IndividualEntity>();
        for (IndividualEntity individualEntity1 : reproducingPairs.keySet()) {
            IndividualEntity individualEntity2 = reproducingPairs.get(individualEntity1);
            if (!individualEntity1.isInside(individualEntity2))
                nonexistentReproducingPairs.put(individualEntity1, individualEntity2);
        }
        return nonexistentReproducingPairs;
    }

    private boolean isPopulationSmallEnoughToReproduce() {
        return population.size() < STOP_REPRODUCING_POPULATION_SIZE;
    }

    private boolean isIndividualEntityReproducing(final IndividualEntity individualEntity) {
        return (reproducingPairs.containsKey(individualEntity) || reproducingPairs.containsValue(individualEntity));
    }
}
