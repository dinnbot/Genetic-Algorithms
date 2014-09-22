package de.licentiouscreativity.sentence;

import java.util.ArrayList;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class SentenceEvolution extends AbstractEvolution{

    public SentenceEvolution() {
        super(500, 0.05f, 0.7f);
    }

    @Override
    protected Individual createNewIndividual(ArrayList<Integer> chromosome) {
        return new SentenceIndividual(chromosome);
    }

    @Override
    protected Individual createNewIndividual() {
        return new SentenceIndividual();
    }

    @Override
    protected int getChromosomeSize() {
        return SentenceIndividual.CHROMOSOME_SIZE;
    }
}
