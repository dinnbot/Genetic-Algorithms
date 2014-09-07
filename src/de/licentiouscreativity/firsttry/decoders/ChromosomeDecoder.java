package de.licentiouscreativity.firsttry.decoders;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Finn on 07.09.14.
 */
public class ChromosomeDecoder {

    private static ChromosomeDecoder instance;

    public static ChromosomeDecoder getInstance() {
        if (ChromosomeDecoder.instance == null) {
            ChromosomeDecoder.instance = new ChromosomeDecoder();
        }
        return ChromosomeDecoder.instance;
    }

    private HashMap<String, String> genValues;

    private ChromosomeDecoder() {
        genValues = new HashMap<String, String>();

        genValues.put("0000", "0");
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

    public String decodeChromosomeToTerm(final String chromosome) {
        ArrayList<String> gens = getGens(chromosome);
        StringBuffer term = new StringBuffer();

        int nextChar = 1;
        for (int i = 0; i < gens.size(); i++) {
            String gen = gens.get(i);

            //int
            if (nextChar == 1) {
                if (Integer.parseInt(gen) <= 1001) {
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

        return term.toString();
    }

    public int decodeChromosomeToResult(final String chromosome) {
        String term = decodeChromosomeToTerm(chromosome);
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

    private ArrayList<String> getGens(final String chromosome) {
        ArrayList<String> gens = new ArrayList<String>();

        for (int i = 0; i < chromosome.length()/4; i++) {
            int genIndex = i*4;
            gens.add(chromosome.substring(genIndex, genIndex+4));
        }

        return gens;
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
