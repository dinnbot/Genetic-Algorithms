package de.licentiouscreativity.evolvingcircles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Finn on 07.09.14.
 */
public abstract class AbstractChromosomeHelper {

    protected final int chromosomeSize;
    protected HashMap<String, String> genValues;
    private final Random random;

    public AbstractChromosomeHelper(final int chromosomeSize) {
        this.chromosomeSize = chromosomeSize;
        random = new Random();
        genValues = new HashMap<String, String>();

        fillGenValues();
    }

    /**
     * Changes a random bit of the chromosome.
     * @param chromosome to mutate.
     */
    public void mutate(final ArrayList<Integer> chromosome) {
        int i = random.nextInt(chromosome.size());
        chromosome.set(i, 1-chromosome.get(i));
    }

    /**
     * Creates and returns randomly generated genes.
     * @param amount of genes to create.
     * @return ArrayList of genes.
     */
    public ArrayList<Integer> createRandomGenes(final int amount) {
        ArrayList<Integer> genes = new ArrayList<Integer>();

        for (int i = 0; i < amount; i++) {
            genes.add(random.nextInt(2));
        }

        return genes;
    }

    public String decodeChromosomeToResult(final ArrayList<Integer> chromosome) {
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < chromosome.size(); i+=chromosomeSize) {
            StringBuffer gen = new StringBuffer();
            for (int j = 0; j < chromosomeSize; j++) {
                gen.append(chromosome.get(j + i));
            }
            result.append(genValues.get(gen.toString()));
        }
        return result.toString();
    }

    protected abstract void fillGenValues();
}
