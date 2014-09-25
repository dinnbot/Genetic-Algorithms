package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class Evolution {

    public final static int CHROMOSOME_SIZE = 17;
    private final static float MUTATION_RATE = .2f;
    private final static float CROSSOVER_RATE = .7f;
    private static volatile Evolution instance;

    private final Random random;
    private final ChromosomeHelper chromosomeHelper;

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

    private Evolution() {
        random = new Random();
        chromosomeHelper = ChromosomeHelper.getInstance();
    }

    public List<String> createChromosomesForInitialPopulation(final int populationSize) {
        List<String> chromosomesForInitialPopulation = new ArrayList<String>();
        for (int i = 0; i < populationSize; i++) {
            chromosomesForInitialPopulation.add(createRandomChromosome());
        }
        return chromosomesForInitialPopulation;
    }

    public String reproduceToNewChromosome(final Individual individual1, final Individual individual2) {
        String newChromosome = individual1.getChromosome();

        if (isCrossoverHappening()) newChromosome = crossover(individual1, individual2);
        if (isMutationHappening()) newChromosome = chromosomeHelper.mutate(newChromosome);

        return newChromosome;
    }

    private boolean isCrossoverHappening() {
        return random.nextDouble() < CROSSOVER_RATE;
    }

    private String crossover(final Individual individual1, final Individual individual2) {
        StringBuffer chromosome = new StringBuffer();

        int randomPoint = random.nextInt(individual1.getChromosomeSize());
        int i;
        for (i = 0; i < randomPoint; i++) {
            chromosome.append(individual1.getChromosome().charAt(i));
        }
        for (; i < CHROMOSOME_SIZE; i++) {
            chromosome.append(individual2.getChromosome().charAt(i));
        }

        return chromosome.toString();
    }

    private boolean isMutationHappening() {
        return random.nextDouble() < MUTATION_RATE;
    }

    private String createRandomChromosome() {
        return chromosomeHelper.createRandomGenes(CHROMOSOME_SIZE);
    }
}
