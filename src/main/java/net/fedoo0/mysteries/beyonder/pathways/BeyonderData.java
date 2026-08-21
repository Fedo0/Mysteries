package net.fedoo0.mysteries.beyonder.pathways;

import java.util.Map;


public class BeyonderData {
    private final String pathway;
    private int sequence;
    private int spirituality;
    private int maxSpirituality;
    private double digestion;
    private double madness;

    public BeyonderData(String pathway, int sequence) {
        this.pathway = pathway;
        this.sequence = sequence;
        this.maxSpirituality = getMaxSpirituality(pathway, sequence);
        this.spirituality = maxSpirituality;
        this.digestion = 0.0;
        this.madness = 0.0;
    }

    //add a spirit lookup table
    // use the Logistic Sigmoid Function for acting -> spirit
    public String getPathway() { return this.pathway; }

    // Sequence functions
    public int getSequence() { return this.sequence; }
    public void setSequence(int sequence) { this.sequence = sequence; }

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
    public static final Map<String, int[]> spiritMap = Map.ofEntries(
        Map.entry("fool", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("error", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("door", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("darkness", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("death", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("twilight_giant", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("visionary", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("sun", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("tyrant", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("white_tower", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("hanged_man", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("justiciar", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("demoness", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("chained", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("abyss", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("red_priest", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("black_emperor", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("mother", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("moon", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("wheel_of_fortune", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("paragon", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20}),
        Map.entry("hermit", new int[]{10000, 5000, 2500, 1200, 600, 300, 150, 80, 40, 20})
    );
    // add an acting modifier 
    private static int getMaxSpirituality(String pathway, int sequence) {
        int[] table = spiritMap.get(pathway);
        return table[sequence];
    }

    


}