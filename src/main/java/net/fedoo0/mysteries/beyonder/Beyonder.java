package net.fedoo0.mysteries.beyonder;

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


    public void registerBeyonder(UUID uuid,String pathway, int sequence) {
        BeyonderData beyonderData = new BeyonderData(pathway, sequence);
        beyonderRegistry.put(uuid, beyonderData);
        setDirty();
    }

    public boolean isBeyonder(UUID uuid) { return beyonderRegistry.containsKey(uuid);}

    public BeyonderData getBeyonder(UUID uuid) {return beyonderRegistry.get(uuid);}

    public void removeBeyonder(UUID uuid) { beyonderRegistry.remove(uuid); setDirty();}

    public ConcurrentHashMap<UUID, BeyonderData> getBeyonderRegistry() {
        return beyonderRegistry;
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
