package impl;

import stubs.Part;
import stubs.PartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {

    Map<UUID, Part> partMap;
    List<Part> partList;

    @Override
    public List<Part> getPartList() throws RemoteException{
        return partList;
    }

    @Override
    public void addPart(Part part) throws RemoteException {
        partList.add(part);
        partMap.put(part.getId(), part);
    }

    @Override
    public void addPart(String name, String description) throws RemoteException {
        Part part = new PartsResource(name, description);
        addPart(part);
    }

    @Override
    public Part getPart(UUID partId) throws RemoteException {
        return partMap.get(partId);
    }

    public PartRepositoryImpl() throws RemoteException {
        super();
    }

    public String besta() throws RemoteException {
        return "metodo besta";
    }
}
