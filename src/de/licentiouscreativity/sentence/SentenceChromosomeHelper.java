package de.licentiouscreativity.sentence;

import java.util.ArrayList;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public class SentenceChromosomeHelper extends AbstractChromosomeHelper{

    private static SentenceChromosomeHelper instance;

    public static SentenceChromosomeHelper getInstance() {
        if (SentenceChromosomeHelper.instance == null) {
            SentenceChromosomeHelper.instance = new SentenceChromosomeHelper();
        }
        return SentenceChromosomeHelper.instance;
    }

    private SentenceChromosomeHelper() {
        super(SentenceIndividual.GEN_SIZE);
    }

    @Override
    protected void fillGenValues() {
        genValues.put("00000", " ");
        genValues.put("00001", "a");
        genValues.put("00010", "b");
        genValues.put("00011", "c");
        genValues.put("00100", "d");
        genValues.put("00101", "e");
        genValues.put("00110", "f");
        genValues.put("00111", "g");
        genValues.put("01000", "h");
        genValues.put("01001", "i");
        genValues.put("01010", "j");
        genValues.put("01011", "k");
        genValues.put("01100", "l");
        genValues.put("01101", "m");
        genValues.put("01110", "n");
        genValues.put("01111", "o");
        genValues.put("10000", "p");
        genValues.put("10001", "q");
        genValues.put("10010", "r");
        genValues.put("10011", "s");
        genValues.put("10100", "t");
        genValues.put("10101", "u");
        genValues.put("10110", "v");
        genValues.put("10111", "w");
        genValues.put("11000", "x");
        genValues.put("11001", "y");
        genValues.put("11010", "z");
        genValues.put("11011", "!");
        genValues.put("11100", "?");
        genValues.put("11101", ",");
        genValues.put("11110", ".");
        genValues.put("11111", "-");
    }
}
