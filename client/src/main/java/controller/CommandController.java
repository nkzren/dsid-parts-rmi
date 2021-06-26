package controller;

import stubs.Part;
import stubs.PartRepository;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class CommandController {

    Scanner sc;
    PartRepository repository;
    private Part currentPart;
    Map<UUID, Integer> currentSubParts;

    public CommandController(Scanner sc, String serverName) throws Exception {
        this.sc = sc;
        this.repository = (PartRepository) Naming.lookup(serverName);
        this.currentPart = null;
        this.currentSubParts = new HashMap<>();
    }

    public Part newPartCommand() throws RemoteException {
        System.out.println("Digite o nome da part:");
        String name = sc.nextLine();

        System.out.println("Digite a descricao da part:");
        String description = sc.nextLine();

        Part newPart = null;

        try {
            if (name.isEmpty() || description.isEmpty()) {
                throw new IllegalArgumentException();
            }

            newPart = this.repository.addPart(name, description);

            System.out.println("id=" + newPart.getId());

            for (UUID partId : currentSubParts.keySet()) {
                repository.addSubpart(newPart, findPart(partId), currentSubParts.get(partId));
            }

            if(this.currentPart == null)
                this.currentPart = newPart;
        }
        catch (IllegalArgumentException e) {
            System.out.println("O nome e a descricao nao podem ser vazios");
            e.printStackTrace();
        }

        return newPart;
    }

    public void addSubpartCommand(int quant) throws RemoteException {

        if(this.currentPart == null){
            System.out.println("A peca corrente eh nula");
            return;
        }

        currentSubParts.put(this.currentPart.getId(), currentSubParts.getOrDefault(this.currentPart.getId(), 0) + quant);
    }

    public List<Part> listParts() throws RemoteException {
        System.out.println("Listando partes...");
        return repository.getPartList();
    }

    public Part findPart(UUID partId) throws RemoteException {

        Part part = repository.getPart(partId);

        if(part != null)
            this.currentPart = part;

        return part;
    }

    public void changeServer(String serverName) throws Exception {

        this.repository = (PartRepository) Naming.lookup(serverName);
    }

    public Part getCurrentPart() {

        return this.currentPart;
    }

    public Map<UUID, Integer> getCurrentSubParts() {

        return currentSubParts;
    }

    public void clearListCommand() {

        this.currentSubParts.clear();
    }
}
