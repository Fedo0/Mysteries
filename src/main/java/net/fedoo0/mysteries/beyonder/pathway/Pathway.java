package net.fedoo0.mysteries.beyonder.pathway;

import net.minecraft.util.StringRepresentable;

public enum Pathway implements StringRepresentable {
    fool,
    error,
    door,
    darkness,
    death,
    twilight_giant,
    visionary,
    sun,
    tyrant,
    white_tower,
    hanged_man,
    justiciar,
    demoness,
    chained,
    abyss,
    red_priest,
    black_emperor,
    mother,
    moon,
    wheel_of_fortune,
    paragon,
    hermit;


    @Override
    public String getSerializedName() {
        return this.name();
    }



}
