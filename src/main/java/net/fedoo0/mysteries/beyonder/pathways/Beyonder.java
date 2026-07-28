package net.fedoo0.mysteries.beyonder.pathways;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Beyonder {
    private final ConcurrentHashMap<UUID, BeyonderData> beyonderRegistry = new ConcurrentHashMap<>();


    public void registerBeyonder(UUID uuid,String pathway, int sequence) {
        BeyonderData beyonderData = new BeyonderData(pathway, sequence);
        beyonderRegistry.put(uuid, beyonderData);
    }


    public boolean isBeyonder(UUID uuid) { return beyonderRegistry.containsKey(uuid);}

    public BeyonderData getBeyonder(UUID uuid) {return beyonderRegistry.get(uuid);}

    public void removeBeyonder(UUID uuid) { beyonderRegistry.remove(uuid);}

    public ConcurrentHashMap<UUID, BeyonderData> getBeyonderRegistry() {
        return beyonderRegistry;
    }
}
