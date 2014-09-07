package de.licentiouscreativity.firsttry.models;

import de.licentiouscreativity.firsttry.decoders.ChromosomeDecoder;

/**
 * Created by Finn on 06.09.14.
 */
public class TermModel {

    private final String chromosome;
    private final String term;
    private float fitness;

    private final int result;

    public TermModel(final String chromosome) {
        this.chromosome = chromosome;

        ChromosomeDecoder chromosomeDecoder = ChromosomeDecoder.getInstance();
        this.term = chromosomeDecoder.decodeChromosomeToTerm(chromosome);
        this.result = chromosomeDecoder.decodeChromosomeToResult(chromosome);
    }

    public void setFitness(final float fitness) { this.fitness = fitness; }

    public float getFitness() { return fitness; }

    public int getResult() {
        return result;
    }

    public String getTerm() {
        return term;
    }

    public String getChromosome() {
        return chromosome;
    }
}
