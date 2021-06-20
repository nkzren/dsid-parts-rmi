package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;
import java.util.UUID;

public interface Part extends Remote {

    UUID getId() throws RemoteException;
    String getName() throws RemoteException;
    String getDescription() throws RemoteException;
    Map<UUID, Integer> getSubcomponents() throws RemoteException;
    void addSubcomponents(Part part) throws RemoteException;
    void addSubcomponents(String name, String description) throws RemoteException;
    public void addSubcomponents(Part part, int quant) throws RemoteException;
}
