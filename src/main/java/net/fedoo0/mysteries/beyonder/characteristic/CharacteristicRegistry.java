package net.fedoo0.mysteries.beyonder.characteristic;

import net.fedoo0.mysteries.beyonder.pathway.Pathway;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class CharacteristicRegistry extends SavedData {
    private static final Map<UUID, Characteristic> characteristicRegistry= new HashMap<>();

    public void registerCharacteristic(Characteristic characteristic) {
        characteristicRegistry.put(characteristic.getId(), characteristic);
        setDirty();
    }

    public Set<Characteristic> getCharacteristics(UUID owner) {
        Set<Characteristic> characteristicSet = new HashSet<>();
        for (Characteristic characteristic : characteristicRegistry.values()) {
            if (owner.equals(characteristic.getOwner())) {
                characteristicSet.add(characteristic);
            }
        }
        return characteristicSet;
    }

    public int getCharacteristicAmount(Pathway pathway, int sequence) {
        int i = 0;
        for (Characteristic characteristic : characteristicRegistry.values()) {
            if (characteristic.getPathway().equals(pathway) && characteristic.getSequence() == sequence) {
                i++;
            }
        }
        return i;
    }






    public static CharacteristicRegistry create() {return new CharacteristicRegistry();}

    public static CharacteristicRegistry load(CompoundTag tag, HolderLookup.Provider provider) {
        CharacteristicRegistry registry = CharacteristicRegistry.create();
        ListTag list = tag.getList("characteristics", CompoundTag.TAG_COMPOUND);
        for (int i=0; i<list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            UUID id = entry.getUUID("id");
            UUID owner = entry.hasUUID("owner")
                    ? entry.getUUID("owner")
                    :null;
            Pathway pathway = Pathway.valueOf(entry.getString("pathway"));
            int sequence = entry.getInt("sequence");

            Characteristic characteristic = new Characteristic(id, owner, pathway, sequence);
            characteristicRegistry.put(id, characteristic);
        }
        return registry;
    }
    @Override
    @NotNull
    public CompoundTag save(CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        ListTag list = new ListTag();
        characteristicRegistry.forEach((id, characteristic) -> {
                CompoundTag entry = new CompoundTag();
                entry.putUUID("id", id);
                if (!(characteristic.getOwner() == null)) {
                    entry.putUUID("owner", characteristic.getOwner());
                }
                entry.putString("pathway", characteristic.getPathway().toString());
                entry.putInt("sequence", characteristic.getSequence());
                list.add(entry);
        });
        tag.put("characteristics", list);
        return tag;
    }

    public static CharacteristicRegistry get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(CharacteristicRegistry::create, CharacteristicRegistry::load),
                "characteristic_data"
        );
    }
}
