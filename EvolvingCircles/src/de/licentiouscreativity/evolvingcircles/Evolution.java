package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.AICircleEntity;
import de.licentiouscreativity.evolvingcircles.entities.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class Evolution {

    private static volatile Evolution instance;

    public static Evolution getInstance() {
        if (Evolution.instance == null) {
            createInstance();
        }
        return Evolution.instance;
    }

    private static synchronized void createInstance() {
        if (Evolution.instance == null) {
            Evolution.instance = new Evolution();
        }
    }

    private final float mutationRate = 0.05f;
    private final float crossoverRate = 0.6f;
    private final int chromosomeSize = 20;

    private final Random random;
    private final ChromosomeHelper chromosomeHelper;

    private Evolution() {
        random = new Random();
        chromosomeHelper = ChromosomeHelper.getInstance();
    }

    public List<String> initPopulation(final int populationSize) {
        List<String> population = new ArrayList<String>();

        for (int i = 0; i < populationSize; i++) {
            population.add(chromosomeHelper.createRandomGenes(chromosomeSize));
        }

        return population;
    }

    public String cross(final Individual individual1, final Individual individual2) {

            String newChromosome = individual1.getChromosome();

            if (random.nextDouble() < crossoverRate) newChromosome = crossover(individual1, individual2);

            if (random.nextDouble() < mutationRate) newChromosome = chromosomeHelper.mutate(newChromosome);

        return newChromosome;
    }

    private String crossover(final Individual individual1, final Individual individual2) {
        StringBuffer chromosome = new StringBuffer();

        int randomPoint = random.nextInt(individual1.getChromosomeSize());
        int i;
        for (i = 0; i < randomPoint; i++) {
            chromosome.append(individual1.getChromosome().charAt(i));
        }
        for (; i < chromosomeSize; i++) {
            chromosome.append(individual2.getChromosome().charAt(i));
        }

        return chromosome.toString();
    }
}
