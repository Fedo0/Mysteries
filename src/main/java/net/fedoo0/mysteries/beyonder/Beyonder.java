package net.fedoo0.mysteries.beyonder;

import net.fedoo0.mysteries.beyonder.characteristic.Characteristic;
import net.fedoo0.mysteries.beyonder.characteristic.CharacteristicRegistry;
import net.fedoo0.mysteries.beyonder.pathway.Pathway;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.saveddata.SavedData;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;



public class Beyonder extends SavedData {

    private final ConcurrentHashMap<UUID, BeyonderData> beyonderRegistry = new ConcurrentHashMap<>();


    public void registerBeyonder(UUID uuid, Pathway pathway, int sequence, ServerLevel level) {

        BeyonderData beyonderData = new BeyonderData(pathway, sequence);
        beyonderRegistry.put(uuid, beyonderData);
        setDirty();
        Characteristic characteristic = new Characteristic(uuid, pathway, sequence);
        CharacteristicRegistry.get(level).registerCharacteristic(characteristic);

    }

    public boolean isBeyonder(UUID uuid) { return beyonderRegistry.containsKey(uuid);}

    public BeyonderData getBeyonder(UUID uuid) {return beyonderRegistry.get(uuid);}

    public void removeBeyonder(UUID uuid, ServerLevel level) {
        beyonderRegistry.remove(uuid);
        setDirty();
        for (Characteristic characteristic : CharacteristicRegistry.create().getCharacteristics(uuid)) {
            characteristic.setOwner(null);
            CharacteristicRegistry.get(level).setDirty();
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


    public void drinkPotion(Pathway pathway, int sequence, LivingEntity player) {
        BeyonderData beyonderData = beyonderRegistry.get(player.getUUID());
        if (beyonderData == null) {
            if (sequence == 9) {
                registerBeyonder(player.getUUID(), pathway, sequence, player.getServer().overworld());
            }
            else {
                // loose control or I dont know game design is hard genuinely dont remember what happens will come back to this later
            }
        }
        else {
            if (beyonderData.getPathway().equals(pathway) && beyonderData.getSequence() == sequence + 1) {
                advance(player.getUUID()); // maybe do different pathways later, but then Ill have to write ability access tied to chars (
            }
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
            BeyonderData beyonderData = new BeyonderData(Pathway.valueOf(entry.getString("pathway")), entry.getInt("sequence"));
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
            entry.putString("pathway", beyonderData.getPathway().toString());
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
