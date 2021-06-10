package impl;

import stubs.PartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {
    public PartRepositoryImpl() throws RemoteException {
        super();
    }

    @Override
    public String besta() throws RemoteException {
        return "metodo besta";
    }
}
