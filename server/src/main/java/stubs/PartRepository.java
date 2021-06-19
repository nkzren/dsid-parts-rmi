package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

public interface PartRepository extends Remote {

    List<Part> getPartList() throws RemoteException;
    void addPart(Part part) throws RemoteException;
    void addPart(String name, String description) throws RemoteException;
    Part getPart(UUID partId) throws RemoteException;
}
