package net.fedoo0.mysteries.beyonder.pathways;

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
        this.maxSpirituality = maxSpirituality;
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


    public void modifyMadness(int amount) {
        this.madness = Math.clamp(this.madness + amount, 0, 1.0);
    }
}
