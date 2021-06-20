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
    Part currentPart;
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

            for (UUID partId : currentSubParts.keySet()) {
                newPart.addSubcomponents(findPart(partId), currentSubParts.get(partId));
            }

//            System.out.println("Deseja adicionar subpart? [s/n]");
//            String option = sc.nextLine();
//
//            if(option.equals("s")) {
//                addSubpartCommand(newPart);
//            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("O nome e a descricao nao podem ser vazios");
            e.printStackTrace();
        }

        if(this.currentPart == null)
            this.currentPart = newPart;

        return newPart;
    }

//    public void addSubpartCommand(Part part, int quant) throws RemoteException {

//        System.out.println("Essa subpart ja existe? [s/n]");
//        String option = sc.nextLine();

//        UUID subId;

//        if (option.equals("n")) {
//            Part newSubpart = newPartCommand();
//            subId = newSubpart.getId();
//        } else {
//            System.out.print("Insira o ID da parte que deseja adicionar: ");
//            subId = UUID.fromString(sc.nextLine());
//        }

//        part.addSubcomponents(findPart(subId), quant);
//    }

//    public void addSubpartCommand(UUID partId, int quant) throws RemoteException {
//
//        addSubpartCommand(findPart(partId), quant);
//    }

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

    public void clearListCommand() {

        this.currentSubParts.clear();
    }
}
