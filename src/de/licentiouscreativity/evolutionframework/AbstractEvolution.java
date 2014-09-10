package de.licentiouscreativity.evolutionframework;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public abstract class AbstractEvolution {

    private final int populationSize;
    private final float mutationRate;
    private final float crossoverRate;

    protected final Random random;
    protected ArrayList<Individual> population;
    protected int generationCount;
    protected double totalFitness;

    public AbstractEvolution(final int populationSize, final float mutationRate, final float crossoverRate) {
        this.populationSize = populationSize;
        this.mutationRate = mutationRate;
        this.crossoverRate = crossoverRate;

        generationCount = 1;
        random = new Random();
        population = new ArrayList<Individual>();

        for (int i = 0; i < populationSize; i++) {
            population.add(createNewIndividual());
        }
    }

    public void live() {
        Individual bestIndividual = getBestIndividual();
        System.out.println("Best Individual: " + bestIndividual.getResult() + "-" + bestIndividual.getFitnessScore());

        evaluateTotalFitness();

        ArrayList<Individual> newPopulation = new ArrayList<Individual>();
        for (int i = 0; i < populationSize; i++) {
            Individual oldIndividual1 = selection();
            Individual oldIndividual2 = selection();

            Individual newIndividual = oldIndividual1;

            if (random.nextDouble() < crossoverRate) newIndividual = crossover(oldIndividual1, oldIndividual2);

            if (random.nextDouble() < mutationRate) newIndividual.mutate();

            newPopulation.add(newIndividual);
        }

        population = newPopulation;
        generationCount++;
    }

    public Object getBestResult() {
        return getBestIndividual().getResult();
    }

    public int getGenerationCount() { return generationCount; }

    protected abstract Individual createNewIndividual(final ArrayList<Integer> chromosome);
    protected abstract Individual createNewIndividual();
    protected abstract int getChromosomeSize();

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

    private Individual selection() {
        double randomNumber = random.nextDouble() * totalFitness;
        int i;
        for (i = 0; i < populationSize && randomNumber > 0; i++) {
            randomNumber -= population.get(i).getFitnessScore();
        }

        return population.get(i-1);
    }

    private void evaluateTotalFitness() {
        totalFitness = 0;
        for (Individual individual : population) {
            totalFitness += individual.getFitnessScore();
        }
    }

    private Individual getBestIndividual() {
        Individual bestIndividual = population.get(0);

        for (Individual individual : population) {
            if (individual.getFitnessScore() > bestIndividual.getFitnessScore()) bestIndividual = individual;
        }

        return bestIndividual;
    }
}
