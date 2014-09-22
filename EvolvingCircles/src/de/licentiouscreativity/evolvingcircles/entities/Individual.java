package de.licentiouscreativity.evolvingcircles.entities;

import java.util.ArrayList;

/**
 * Created by finn.meyer on 10.09.2014.
 */
public interface Individual {

    void mutate();

    ArrayList<Integer> getChromosome();
}
