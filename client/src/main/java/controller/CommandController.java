package controller;

import stubs.PartRepository;

import java.rmi.RemoteException;
import java.util.Scanner;

public class CommandController {

    Scanner sc;
    PartRepository repository;

    public CommandController(Scanner sc, PartRepository repository) {
        this.sc = sc;
        this.repository = repository;
    }

    public void newPartCommand() {
        System.out.println("Digite o nome da part:");
        String name = sc.nextLine();

        System.out.println("Digite a descricao da part:");
        String description = sc.nextLine();

        try {
            this.repository.addPart(name, description);
        } catch (RemoteException e) {
            System.out.println("Erro ao adicionar a peca:");
            e.printStackTrace();
        }
    }

}
