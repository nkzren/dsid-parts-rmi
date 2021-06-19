package controller;

import stubs.Part;
import stubs.PartRepository;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CommandController {

    Scanner sc;
    PartRepository repository;

    public CommandController(Scanner sc, String serverName) throws Exception {
        this.sc = sc;
        this.repository = (PartRepository) Naming.lookup(serverName);
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

            System.out.println("Deseja adicionar subpart? [s/n]");
            String option = sc.nextLine();

            if(option.equals("s")) {
                addSubpartCommand(newPart);
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("O nome e a descricao nao podem ser vazios");
            e.printStackTrace();
        }

        return newPart;
    }

    public void addSubpartCommand(Part part) throws RemoteException {

        System.out.println("Essa subpart ja existe? [s/n]");
        String option = sc.nextLine();

        UUID subId;

        if (option.equals("n")) {
            Part newSubpart = newPartCommand();
            subId = newSubpart.getId();
        } else {
            System.out.print("Insira o ID da parte que deseja adicionar: ");
            subId = UUID.fromString(sc.nextLine());
        }

        part.addSubcomponents(findPart(subId));
    }

    public void addSubpartCommand(UUID partId)  throws RemoteException {

        addSubpartCommand(findPart(partId));
    }

    public List<Part> listParts() throws RemoteException {
        System.out.println("Listando partes...");
        return repository.getPartList();
    }

    public Part findPart(UUID partId) throws RemoteException {
        return repository.getPart(partId);
    }

    public void changeServer(String serverName) throws Exception {

        this.repository = (PartRepository) Naming.lookup(serverName);
    }
}
