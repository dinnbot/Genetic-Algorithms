package de.licentiouscreativity.evolutionframework;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public abstract class AbstractLife implements Runnable{

    public static String DIE;
    private AbstractEvolution evolution;
    private boolean live;

    public AbstractLife(final AbstractEvolution evolution, final String die) {
        DIE = die;
        this.evolution = evolution;
        live = true;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (live) {
            if (evolution.getBestResult().equals(DIE)) live = false;
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
