package de.licentiouscreativity.evolvingcircles;

/**
 * Created by finn.meyer on 12.09.2014.
 */
public class Main implements Runnable {

    private static Window window;

    public static void main(String[] args) {
        window = Window.getInstance();

        new Thread(new Main()).start();
    }

    @Override
    public void run() {
        while (true) {
            window.repaint();
            try {
                Thread.sleep(100 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
