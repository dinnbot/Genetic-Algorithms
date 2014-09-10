package de.licentiouscreativity.sentence;

import java.util.ArrayList;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class SentenceIndividual implements Individual{

    public static final int GEN_SIZE = 5;
    public static final int CHROMOSOME_SIZE = SentenceIndividual.GEN_SIZE * Main.RESULT.length();

    private final AbstractChromosomeHelper chromosomeHelper;

    private final ArrayList<Integer> chromosome;
    private String result;

    public SentenceIndividual(final ArrayList<Integer> chromosome) {
        chromosomeHelper = SentenceChromosomeHelper.getInstance();
        this.chromosome = chromosome;
        result = chromosomeHelper.decodeChromosomeToResult(chromosome);
    }

    public SentenceIndividual() {
        chromosomeHelper = SentenceChromosomeHelper.getInstance();
        chromosome = chromosomeHelper.createRandomGenes(SentenceIndividual.CHROMOSOME_SIZE);
        result = chromosomeHelper.decodeChromosomeToResult(chromosome);
    }

    @Override
    public void mutate() {
        chromosomeHelper.mutate(chromosome);
    }

    @Override
    public double getFitnessScore() {
        char[] resultArray = result.toCharArray();
        char[] wantedArray = Main.RESULT.toCharArray();

        int fitnessScore = 0;
        for (int i = 0; i < resultArray.length && i < wantedArray.length; i++) {
            if (resultArray[i] == wantedArray[i]) fitnessScore++;
        }

        return fitnessScore;
    }

    @Override
    public String getResult() {
        return result;
    }

    @Override
    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }
}
