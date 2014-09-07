package de.licentiouscreativity.firsttry;

import de.licentiouscreativity.firsttry.decoders.ChromosomeDecoder;

/**
 * Created by Finn on 06.09.14.
 */
public class Main {

    public static void main(String[] args) {
        ChromosomeDecoder td = ChromosomeDecoder.getInstance();
        System.out.println(td.decodeChromosomeToResult("0010001010101110101101110010"));
    }
}
