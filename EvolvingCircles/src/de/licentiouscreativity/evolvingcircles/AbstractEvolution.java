package de.licentiouscreativity.evolvingcircles;

import de.licentiouscreativity.evolvingcircles.entities.Individual;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class AbstractEvolution {

    private final int populationSize;
    private final float mutationRate;
    private final float crossoverRate;

    protected final Random random;

    public AbstractEvolution(final int populationSize, final float mutationRate, final float crossoverRate) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;

        random = new Random();
    }

    public Individual cross(final Individual individual1, final Individual individual2) {

            Individual newIndividual = individual1;

            if (random.nextDouble() < crossoverRate) newIndividual = crossover(individual1, individual2);

            if (random.nextDouble() < mutationRate) newIndividual.mutate();

        return newIndividual;
    }

    private Individual crossover(final Individual individual1, final Individual individual2) {
        ArrayList<Integer> chromosome = new ArrayList<Integer>();

        int randomPoint = random.nextInt(getChromosomeSize());
        int i;
        for (i = 0; i < randomPoint; i++) {
            chromosome.add(individual1.getChromosome().get(i));
        }
        for (; i < getChromosomeSize(); i++) {
            chromosome.add(individual2.getChromosome().get(i));
        }

        return createNewIndividual(chromosome);
    }
}
