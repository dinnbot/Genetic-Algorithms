package de.licentiouscreativity.evolvingcircles.entities;

import de.licentiouscreativity.evolvingcircles.ChromosomeHelper;

import java.awt.*;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class AICircleEntity extends AbstractCircleEntity implements Individual{

    private final String chromosome;
    private final double multiX, multiY;

    public AICircleEntity(final int posX, final int posY, final int radius, final String chromosome) {
        super(posX, posY, radius, Color.GRAY);
        this.chromosome = chromosome;

        ChromosomeHelper chromosomeHelper = ChromosomeHelper.getInstance();

        multiX = chromosomeHelper.decodeToDouble(chromosome.substring(0, (chromosome.length() + 1) / 2));
        multiY = chromosomeHelper.decodeToDouble(chromosome.substring((chromosome.length() + 1) / 2));
    }

    public void update(final float delta, final int pDirX, final int pDirY) {
        dirX = (int) (pDirX*multiX);
        dirY = (int) (pDirY*multiY);
        super.update(delta);
    }

    @Override
    public int getChromosomeSize() {
        return chromosome.length();
    }

    @Override
    public String getChromosome() {
        return chromosome;
    }
}
