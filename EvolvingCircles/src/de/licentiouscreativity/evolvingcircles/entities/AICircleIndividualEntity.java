package de.licentiouscreativity.evolvingcircles.entities;

import com.sun.javafx.geom.Vec2d;
import de.licentiouscreativity.evolvingcircles.ChromosomeHelper;
import de.licentiouscreativity.evolvingcircles.Evolution;

import java.awt.*;

/**
 * Created by finn.meyer on 22.09.2014.
 */
public class AICircleIndividualEntity extends AbstractCircleEntity implements IndividualEntity{

    private final static Color COLOR = Color.GRAY;
    private final static Vec2d MULTI_X_ALLELE_RANGE = new Vec2d(0, 6);
    private final static Vec2d MULTI_Y_ALLELE_RANGE = new Vec2d(6, 12);
    private final static Vec2d RADIUS_ALLELE_RANGE = new Vec2d(12, 17);

    private final String chromosome;
    private final double multiX, multiY;
    private final PlayerCircleEntity player;

    public AICircleIndividualEntity(final int posX, final int posY, final String chromosome) {
        super(posX, posY, 0, COLOR);
        this.chromosome = chromosome;

        multiX = getMultiXFromChromosome();
        multiY = getMultiYFromChromosome();
        radius = getRadiusFromChromosome();

        player = PlayerCircleEntity.getInstance();
    }

    public void update(final float delta) {
        dirX = (int) (player.getDirX()*multiX);
        dirY = (int) (player.getDirY()*multiY);
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

    private double getMultiXFromChromosome() {
        String allele = getAllele(MULTI_X_ALLELE_RANGE);
        return ChromosomeHelper.decodeToDouble(allele);
    }

    private double getMultiYFromChromosome() {
        String allele = getAllele(MULTI_Y_ALLELE_RANGE);
        return ChromosomeHelper.decodeToDouble(allele);
    }

    private int getRadiusFromChromosome() {
        String allele = getAllele(RADIUS_ALLELE_RANGE);
        return ChromosomeHelper.decodeToInt(allele);
    }

    private String getAllele(final Vec2d alleleRange) {
        return chromosome.substring((int) alleleRange.x, (int) alleleRange.y);
    }
}
