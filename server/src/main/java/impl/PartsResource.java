package impl;

import stubs.Part;

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
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Map<UUID, Integer> getSubcomponents() {

        return subcomponents;
    }

    public void addSubcomponents(Part part) {

        subcomponents.put(part.getId(), subcomponents.getOrDefault(subcomponents.get(part), 0) + 1);
    }

    public void addSubcomponents(String name, String description) {

        Part part = new PartsResource(name, description);
        addSubcomponents(part);
    }
}
