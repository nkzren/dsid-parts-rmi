package controller;

import stubs.Part;
import stubs.PartRepository;

import java.rmi.Naming;
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

    public Optional<Part> newPartCommand() {
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

            if(this.currentPart == null) {
                this.currentPart = newPart;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("O nome e a descricao nao podem ser vazios");
            e.printStackTrace();
        } catch (RemoteException e) {
            System.out.println("Erro de conexao com o servidor");
            return Optional.empty();
        }

        if (newPart != null) {
            return Optional.of(newPart);
        } else {
            return Optional.empty();
        }
    }

    public void addSubpartCommand(int quant) {

        if(this.currentPart == null){
            System.out.println("A peca corrente eh nula");
            return;
        }

        try {
            UUID currentId = this.currentPart.getId();
            currentSubParts.put(currentId, currentSubParts.getOrDefault(currentId, 0) + quant);
        } catch (RemoteException e) {
            handleRemoteException(e);
        }
    }

    public Optional<List<Part>> listParts() {
        try {
            System.out.println("Listando partes...");
            return Optional.of(repository.getPartList());
        } catch (RemoteException e) {
            handleRemoteException(e);
            return Optional.empty();
        }
    }

    public Optional<Part> findPart(UUID partId) {


        try {
            return Optional.of(repository.getPart(partId));
        } catch (RemoteException e) {
            handleRemoteException(e);
            return Optional.empty();
        }
    }

    public void changeServer(String serverName) throws Exception {

        this.repository = (PartRepository) Naming.lookup(serverName);
    }

    public Optional<Part> getCurrentPart() throws RemoteException {
        return findPart(this.currentPart.getId());
    }

    public void setCurrentPart(Part currentPart) {

        this.currentPart = currentPart;
    }

    public Map<UUID, Integer> getCurrentSubParts() {

        return currentSubParts;
    }

    public void handleRemoteException(RemoteException e) {
        System.out.println("Erro de conexao com o servidor");
        e.printStackTrace();
    }

    public void clearListCommand() {

        this.currentSubParts.clear();
    }
}
