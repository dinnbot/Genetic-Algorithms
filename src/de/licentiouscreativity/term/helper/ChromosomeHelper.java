package de.licentiouscreativity.term.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by Finn on 07.09.14.
 */
public class ChromosomeHelper {

    private static ChromosomeHelper instance;
    private final static int CHROMOSOME_SIZE = 4;

    public static ChromosomeHelper getInstance() {
        if (ChromosomeHelper.instance == null) {
            ChromosomeHelper.instance = new ChromosomeHelper();
        }
        return ChromosomeHelper.instance;
    }

    private HashMap<String, String> genValues;
    private final Random random;

    private ChromosomeHelper() {
        random = new Random();
        genValues = new HashMap<String, String>();

        genValues.put("0000", "");
        genValues.put("0001", "1");
        genValues.put("0010", "2");
        genValues.put("0011", "3");
        genValues.put("0100", "4");
        genValues.put("0101", "5");
        genValues.put("0110", "6");
        genValues.put("0111", "7");
        genValues.put("1000", "8");
        genValues.put("1001", "9");
        genValues.put("1010", "+");
        genValues.put("1011", "-");
        genValues.put("1100", "*");
        genValues.put("1101", "/");
        genValues.put("1110", "");
        genValues.put("1111", "");
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

    /**
     * Decodes a chromosome to a term and returns it.
     * @param chromosomes to be decoded.
     * @return String mathematical term.
     */
    public String decodeChromosomeToTerm(final ArrayList<Integer> chromosomes) {
        StringBuffer term = new StringBuffer();

        int nextChar = 1;
        for (int i = 0; i < chromosomes.size(); i+=CHROMOSOME_SIZE) {
            StringBuffer tempGen = new StringBuffer();
            for (int j = 0; j < CHROMOSOME_SIZE; j++) {
                tempGen.append(chromosomes.get(j+i));
            }
            String gen = tempGen.toString();

            //int
            if (nextChar == 1) {
                if (Integer.parseInt(gen) > 0 && Integer.parseInt(gen) <= 1001) {
                    term.append(genValues.get(gen));
                    nextChar = 2;
                }
            //operator
            } else {
                if (Integer.parseInt(gen) > 1001 && Integer.parseInt(gen) < 1110) {
                    term.append(genValues.get(gen));
                    nextChar = 1;
                }
            }
        }

        if (term.length() > 0) {
            //If last character is an operator, remove it.
            String lastChar = term.substring(term.length() - 1);
            if (lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("*") || lastChar.equals("/"))
                term.deleteCharAt(term.length() - 1);
        }

        return term.toString();
    }

    public int decodeChromosomeToResult(final ArrayList<Integer> chromosomes) {
        String term = decodeChromosomeToTerm(chromosomes);
        int result = 0;

        char[] chars = term.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                result = Integer.parseInt(Character.toString(chars[i]));
            } else {
                result = operate(result, Integer.parseInt(Character.toString(chars[i + 1])), chars[i]);
                i++;
            }
        }

        return result;
    }

    private int operate(final int a, final int b, final char operator) {
        switch (operator) {
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                return a/b;
        }

        return 0;
    }
}
