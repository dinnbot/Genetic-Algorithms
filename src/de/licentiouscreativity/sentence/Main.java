package de.licentiouscreativity.sentence;


import de.licentiouscreativity.term.Evolution;

import java.util.ArrayList;

/**
 * Created by Finn on 06.09.14.
 */
public class Main implements Runnable{

    public final static String RESULT = "be or not to be!";
    private AbstractEvolution evolution;
    private boolean live;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        evolution = new SentenceEvolution();
        live = true;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (live) {
            if (evolution.getBestResult().equals(RESULT)) live = false;
            evolution.live();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("It took " + evolution.getGenerationCount() + " generations.");
    }
}
