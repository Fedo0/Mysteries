package net.fedoo0.mysteries.beyonder.potion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;
import net.minecraft.util.StringRepresentable;

public record BeyonderPotionData(Pathway pathway, int sequence) {
    public static final Codec<Pathway> PATHWAY_CODEC = StringRepresentable.fromEnum(Pathway::values);

    public static final Codec<BeyonderPotionData> CODEC = RecordCodecBuilder.create(instance -> instance.group(

            PATHWAY_CODEC.fieldOf("pathway").forGetter(BeyonderPotionData::pathway),
            Codec.INT.fieldOf("sequence").forGetter(BeyonderPotionData::sequence)
    ).apply(instance, BeyonderPotionData::new));
}
