package de.licentiouscreativity.term;


/**
 * Created by Finn on 06.09.14.
 */
public class Main implements Runnable{

    public final static int RESULT = 100;
    private Evolution evolution;
    private boolean live;

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        evolution = new Evolution();
        live = true;

        new Thread(this).start();
    }

    @Override
    public void run() {
        while (live) {
            if (evolution.getBestResult() == RESULT) live = false;
            evolution.live();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
