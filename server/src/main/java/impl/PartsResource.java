package impl;

import stubs.Part;

import java.rmi.RemoteException;
import java.util.*;

public class PartsResource implements Part {

    private final UUID id;
    private String name;
    private String description;
    private Map<UUID, Integer> subcomponents;

    public PartsResource(String name, String description) {

        this.id = UUID.randomUUID();
        this.name = name;
        this.description = description;

        subcomponents = new HashMap<>();
    }

    @Override
    public UUID getId() throws RemoteException {
        return id;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String getDescription() throws RemoteException {
        return description;
    }

    @Override
    public Map<UUID, Integer> getSubcomponents() throws RemoteException {

        return subcomponents;
    }

    public void addSubcomponents(Part part) throws RemoteException {

        subcomponents.put(part.getId(), subcomponents.getOrDefault(subcomponents.get(part), 0) + 1);
    }

    public void addSubcomponents(String name, String description) throws RemoteException {

        Part part = new PartsResource(name, description);
        addSubcomponents(part);
    }

    @Override
    public String toString() {
        return "PartsResource{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
