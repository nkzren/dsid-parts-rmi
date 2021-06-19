package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartRepository extends Remote {

    List<Part> getPartList();
    void addPart(Part part);
    void addPart(String name, String description);
    Part getPart(UUID partId);
}
