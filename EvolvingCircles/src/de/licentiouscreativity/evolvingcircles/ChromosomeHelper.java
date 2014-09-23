package de.licentiouscreativity.evolvingcircles;

import java.util.Random;
import java.util.function.LongBinaryOperator;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class ChromosomeHelper {

    private static volatile ChromosomeHelper instance;

    public static ChromosomeHelper getInstance() {
        if (ChromosomeHelper.instance == null) {
            createInstance();
        }
        return ChromosomeHelper.instance;
    }

    private static synchronized void createInstance() {
        if (ChromosomeHelper.instance == null) {
            ChromosomeHelper.instance = new ChromosomeHelper();
        }
    }

    private final Random random;

    private ChromosomeHelper() {
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

    public double decodeToDouble(final String chromosome) {
        long number = Long.parseLong(chromosome);
        long dec = 0;
        long po2 = 1;
        long dig;

        while (number > 0) {
            dig = number % 10;
            dec += dig * po2;
            number /= 10;
            po2 *= 2;
        }
        System.out.println(number);
        return 0;
    }
}
