package stubs;

import java.rmi.Remote;
import java.util.Map;
import java.util.UUID;

public interface Part extends Remote {

    UUID getId();
    String getName();
    String getDescription();
    Map<UUID, Integer> getSubcomponents();
    void addSubcomponents(Part part);
    void addSubcomponents(String name, String description);
}
