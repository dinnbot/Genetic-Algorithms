package de.licentiouscreativity.term;

import de.licentiouscreativity.term.models.TermModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Finn on 07.09.14.
 */
public class Evolution {

    public final static int POP_SIZE = 400;
    public final static float MUTATION_RATE = 0.03f;
    public final static float CROSSOVER_RATE = 0.7f;

    private final Random random;
    private ArrayList<TermModel> population;
    private double totalFitness;

    public Evolution() {
        random = new Random();
        population = new ArrayList<TermModel>();

        //first population
        for (int i = 0; i < POP_SIZE; i++) {
            population.add(new TermModel());
        }
    }

    public void live() {
//        for (TermModel term : population) {
//            System.out.println(term.getTerm()+"="+term.getResult() + " - " + term.getFitnessScore());
//        }

        TermModel bestTerm = getBestTerm();
        System.out.println("Best term: " + bestTerm.getTerm() + "=" + bestTerm.getResult());

        evaluateTotalFitness();

        ArrayList<TermModel> newPopulation = new ArrayList<TermModel>();
        for (int i = 0; i < POP_SIZE; i++) {
            TermModel oldTerm1 = selection();
            TermModel oldTerm2 = selection();

            TermModel newTerm = oldTerm1;

            if (random.nextDouble() < CROSSOVER_RATE) newTerm = crossover(oldTerm1, oldTerm2);

            if (random.nextDouble() < MUTATION_RATE) newTerm.mutate();

            newPopulation.add(newTerm);
        }

        population = newPopulation;
    }

    public int getBestResult() {
        return getBestTerm().getResult();
    }

    private TermModel crossover(final TermModel term1, final TermModel term2) {
        ArrayList<Integer> chromosome = new ArrayList<Integer>();

        int randomPoint = random.nextInt(TermModel.CHROMOSOME_LENGTH);
        int i;
        for (i = 0; i < randomPoint; i++) {
            chromosome.add(term1.getChromosome().get(i));
        }
        for (; i < TermModel.CHROMOSOME_LENGTH; i++) {
            chromosome.add(term2.getChromosome().get(i));
        }

        return new TermModel(chromosome);
    }

    private TermModel selection() {
        double randomNumber = random.nextDouble() * totalFitness;
        int i;
        for (i = 0; i < POP_SIZE && randomNumber > 0; i++) {
            randomNumber -= population.get(i).getFitnessScore();
        }

        return population.get(i-1);
    }

    private void evaluateTotalFitness() {
        totalFitness = 0;
        for (TermModel term : population) {
            totalFitness += term.getFitnessScore();
        }
    }

    private TermModel getBestTerm() {
        TermModel bestTerm = population.get(0);

        for (TermModel term : population) {
            if (term.getFitnessScore() > bestTerm.getFitnessScore()) bestTerm = term;
        }

        return bestTerm;
    }
}
