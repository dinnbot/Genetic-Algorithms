package de.licentiouscreativity.evolvingcircles;

import java.util.Random;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class ChromosomeHelper {

    private final Random random;

    public ChromosomeHelper() {
        random = new Random();
    }

    public String mutate(final String chromosome) {
        char[] chromosomeArray = chromosome.toCharArray();
        int i = random.nextInt(chromosome.length());

        chromosomeArray[i] = (char) (1-Character.getNumericValue(chromosomeArray[i]));

        return String.copyValueOf(chromosomeArray);
    }

    public String createRandomGenes(final int amount) {
        StringBuffer chromosome = new StringBuffer();

        for (int i = 0; i < amount; i++) {
            chromosome.append(Integer.toString(random.nextInt(2)));
        }

        return chromosome.toString();
    }

    public float decodeToFloat(final String chromosome) {
        int intBits = Integer.parseInt(chromosome, 2);
        return Float.intBitsToFloat(intBits);
    }
}
