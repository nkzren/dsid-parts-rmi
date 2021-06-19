package controller;

import stubs.Part;
import stubs.PartRepository;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;

public class CommandController {

    Scanner sc;
    PartRepository repository;

    public CommandController(Scanner sc, PartRepository repository) {
        this.sc = sc;
        this.repository = repository;
    }

    public void newPartCommand() throws RemoteException {
        System.out.println("Digite o nome da part:");
        String name = sc.nextLine();

        System.out.println("Digite a descricao da part:");
        String description = sc.nextLine();

        try {
            if (name.isEmpty() || description.isEmpty()) {
                throw new IllegalArgumentException();
            }
            this.repository.addPart(name, description);
        } catch (IllegalArgumentException e) {
            System.out.println("O nome e a descricao nao podem ser vazios");
            e.printStackTrace();
        }
    }

    public List<Part> listParts() throws RemoteException {
        System.out.println("Listando partes...");
        return repository.getPartList();
    }

    public Part findPart() {
        return null;
    }

}
