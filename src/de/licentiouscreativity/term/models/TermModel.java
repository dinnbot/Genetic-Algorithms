package de.licentiouscreativity.term.models;

import de.licentiouscreativity.term.Main;
import de.licentiouscreativity.term.helper.ChromosomeHelper;

import java.util.ArrayList;

/**
 * Created by Finn on 06.09.14.
 */
public class TermModel {

    public final static int CHROMOSOME_LENGTH = 4*12;

    private final ArrayList<Integer> chromosome;
    private final String term;
    private final int result;

    public TermModel() {
        ChromosomeHelper chromosomeHelper = ChromosomeHelper.getInstance();

        chromosome = chromosomeHelper.createRandomGenes(CHROMOSOME_LENGTH);

        this.term = chromosomeHelper.decodeChromosomeToTerm(chromosome);
        this.result = chromosomeHelper.decodeChromosomeToResult(chromosome);
    }

    public TermModel(final ArrayList<Integer> chromosome) {
        ChromosomeHelper chromosomeHelper = ChromosomeHelper.getInstance();

        this.chromosome = chromosome;

        this.term = chromosomeHelper.decodeChromosomeToTerm(chromosome);
        this.result = chromosomeHelper.decodeChromosomeToResult(chromosome);
    }

    public void mutate() { ChromosomeHelper.getInstance().mutate(chromosome);}

    public double getFitnessScore() {
        if (result == 42) return 1;
        return 1.0/(Main.RESULT-result);
    }

    public String getTerm() {
        return term;
    }

    public int getResult() {
        return result;
    }

    public ArrayList<Integer> getChromosome() {
        return chromosome;
    }

}
