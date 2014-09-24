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

        int newGene = 1 - Character.getNumericValue(chromosomeArray[i]);
        
        chromosomeArray[i] = Integer.toString(newGene).charAt(0); //TODO

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
        double algSign = 1;
        double preNumber = Integer.parseInt(chromosome.substring(1, 3), 2);
        double decimalPlace = 0;
        double completeNumber = 0;

        if (chromosome.charAt(0) == '0') algSign = -1;

        String decimalPlaceString = "0" + chromosome.substring(3);
        int i;
        for (i = 0; i < decimalPlaceString.length(); i++) {
            double multi = Math.pow(2, i);
            decimalPlace += (multi * Integer.parseInt(decimalPlaceString.substring(i, i+1)));
        }
        decimalPlace /= Math.pow(2, i);

        completeNumber = (preNumber + decimalPlace) * algSign;
        return completeNumber;
    }
}
