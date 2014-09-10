package de.licentiouscreativity.evolutionframework;

import java.util.ArrayList;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public interface Individual {

    void mutate();

    String getResult();

    ArrayList<Integer> getChromosome();

    double getFitnessScore();
}
