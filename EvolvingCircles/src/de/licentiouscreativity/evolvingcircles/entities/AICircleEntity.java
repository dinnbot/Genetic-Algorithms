package de.licentiouscreativity.evolvingcircles.entities;

import java.awt.*;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class AICircleEntity extends AbstractCircleEntity implements Individual{



    public AICircleEntity(final int posX, final int posY, final int radius) {
        super(posX, posY, radius, Color.GRAY);
    }

    public void update(final float delta, final int pDirX, final int pDirY) {
        dirX = pDirX;
        dirY = pDirY;
        super.update(delta);
    }

    @Override
    public void mutate() {

    }

    @Override
    public ArrayList<Integer> getChromosome() {
        return null;
    }
}
