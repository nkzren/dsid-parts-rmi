package impl;

import stubs.Part;
import stubs.PartRepository;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class PartRepositoryImpl extends UnicastRemoteObject implements PartRepository {

    Map<UUID, Part> partMap;
    List<Part> partList;

    @Override
    public List<Part> getPartList() throws RemoteException{
        return partList;
    }

    public PartRepositoryImpl() throws RemoteException {
        super();

        this.partList = new ArrayList<>();
        this.partMap = new HashMap<>();
    }

    @Override
    public void addPart(Part part) throws RemoteException {
        System.out.println("nova part:" + part.toString());
        partList.add(part);
        partMap.put(part.getId(), part);
    }

    @Override
    public void addPart(String name, String description) throws RemoteException {
        System.out.println(name + description);
        Part part = new PartsResource(name, description);
        addPart(part);
    }

    @Override
    public Part getPart(UUID partId) throws RemoteException {
        return partMap.get(partId);
    }


    public String besta() throws RemoteException {
        return "metodo besta";
    }
}
