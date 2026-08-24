package net.fedoo0.mysteries.beyonder;

import net.fedoo0.mysteries.beyonder.characteristic.Characteristic;
import net.fedoo0.mysteries.beyonder.characteristic.CharasteristicRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



public class Beyonder extends SavedData {

    private final ConcurrentHashMap<UUID, BeyonderData> beyonderRegistry = new ConcurrentHashMap<>();


    public void registerBeyonder(UUID uuid, String pathway, int sequence, ServerLevel level) {

        BeyonderData beyonderData = new BeyonderData(pathway, sequence);
        beyonderRegistry.put(uuid, beyonderData);
        setDirty();
        Characteristic characteristic = new Characteristic(uuid, pathway, sequence);
        CharasteristicRegistry.get(level).registerCharacteristic(characteristic);

    }

    public boolean isBeyonder(UUID uuid) { return beyonderRegistry.containsKey(uuid);}

    public BeyonderData getBeyonder(UUID uuid) {return beyonderRegistry.get(uuid);}

    public void removeBeyonder(UUID uuid, ServerLevel level) {
        beyonderRegistry.remove(uuid);
        setDirty();
        for (Characteristic characteristic : CharasteristicRegistry.create().getCharacteristics(uuid)) {
            characteristic.setOwner(null);
            CharasteristicRegistry.get(level).setDirty();
        }

    }

    public ConcurrentHashMap<UUID, BeyonderData> getBeyonderRegistry() {
        return beyonderRegistry;
    }

    public void advance(UUID uuid) {
        BeyonderData beyonderData = beyonderRegistry.get(uuid);
        if (!(beyonderData == null)) {
            // check the potion's sequence whenever they are done
            // check ritual completion
            // maybe add a process of advancing for higher sequences that can be disrupted. Will do this sometime later
            // I wonder if anyone ever reads the comments, besides me, of course
            // If you do dm me on discord or smth @.fedor.
            // also remove register beyonder or make it so it doesnt create infinite chars. blah blah blah
            beyonderData.setSequence(beyonderData.getSequence()-1);
            setDirty();
        }
    }



    // saving the registry

    public static Beyonder create() {
        return new Beyonder();
    }

    public static Beyonder load(CompoundTag tag, HolderLookup.Provider provider) {
        Beyonder data = Beyonder.create();
        ListTag list = tag.getList("beyonders", CompoundTag.TAG_COMPOUND);
        for (int i=0; i<list.size(); i++) {
            CompoundTag entry = list.getCompound(i);
            UUID uuid = entry.getUUID("uuid");
            BeyonderData beyonderData = new BeyonderData(entry.getString("pathway"), entry.getInt("sequence"));
            beyonderData.modifyMadness(entry.getDouble("madness"));
            beyonderData.modifyDigestion(entry.getDouble("digestion"));
            beyonderData.modifySpirituality(entry.getInt("spirituality")-beyonderData.getSpirituality());

            data.beyonderRegistry.put(uuid, beyonderData);
        }
        return data;
    }

    @Override
    @NotNull
    public CompoundTag save(CompoundTag tag, HolderLookup.@NotNull Provider provider) {
        ListTag list = new ListTag();
        beyonderRegistry.forEach((uuid, beyonderData) -> {
            CompoundTag entry = new CompoundTag();
            entry.putUUID("uuid", uuid);
            entry.putString("pathway", beyonderData.getPathway());
            entry.putInt("sequence", beyonderData.getSequence());
            entry.putDouble("madness", beyonderData.getMadness());
            entry.putDouble("digestion", beyonderData.getDigestion());
            entry.putInt("spirituality", beyonderData.getSpirituality());
            list.add(entry);
        });
        tag.put("beyonders", list);
        return tag;
    }

    public static Beyonder get(ServerLevel overworld) {
        return overworld.getDataStorage().computeIfAbsent(
                new SavedData.Factory<>(Beyonder::create, Beyonder::load),
                "beyonder_data"
        );
    }
    
}
