package net.fedoo0.mysteries.beyonder;

import net.fedoo0.mysteries.beyonder.pathway.Pathway;

import java.util.Map;


public class BeyonderData {
    private final Pathway pathway;
    private int sequence;
    private int spirituality;
    private int maxSpirituality;
    private double digestion;
    private double madness;

    public BeyonderData(Pathway pathway, int sequence) {
        this.pathway = pathway;
        this.sequence = sequence;
        this.maxSpirituality = getMaxSpirituality(pathway, sequence);
        this.spirituality = maxSpirituality;
        this.digestion = 0.0;
        this.madness = 0.0;
    }

    // use the Logistic Sigmoid Function for acting -> spirit
    public Pathway getPathway() { return this.pathway; }

    // Sequence functions
    public int getSequence() { return this.sequence; }
    public void setSequence(int sequence) { this.sequence = sequence;
        this.maxSpirituality = getMaxSpirituality(pathway, sequence);
        this.spirituality = maxSpirituality;
    }

    //Spirituality functions
    public int getSpirituality() { return this.spirituality; }

    public void modifySpirituality(int amount) {
        this.spirituality = Math.clamp(this.spirituality + amount, 0, this.maxSpirituality);
    }

    // Digestion functions
    public double getDigestion() { return this.digestion; }

    public void progressDigestion(double amount) {
        this.digestion = Math.min(1.0, this.digestion + amount);
    }

    public boolean isFullyDigested() {
        return this.digestion >= 1.0;
    }
    // Madness functions
    public double getMadness() {return this.madness;}

    public boolean isMad() {return this.madness>=1;}


    public void modifyMadness(double amount) {
        this.madness = Math.clamp(this.madness + amount, 0, 1.0);
    }

    //Spirit lookup table 
    public static final Map<Pathway, int[]> spiritMap = Map.ofEntries(
        Map.entry(Pathway.fool, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.error, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.door, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.darkness, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.death, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.twilight_giant, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.visionary, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.sun, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.tyrant, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.white_tower, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.hanged_man, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.justiciar, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.demoness, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.chained, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.abyss, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.red_priest, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.black_emperor, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.mother, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.moon, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.wheel_of_fortune, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.paragon, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry(Pathway.hermit, new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20})
    );
    // add an acting modifier 
    private static int getMaxSpirituality(Pathway pathway, int sequence) {
        int[] table = spiritMap.get(pathway);
        return table[sequence];
    }

    public void modifyDigestion(double amount) {this.digestion = Math.clamp(this.digestion + amount, 0, 1.0);}


}