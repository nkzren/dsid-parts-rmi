package stubs;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartRepository extends Remote {
    String besta() throws RemoteException;
}
