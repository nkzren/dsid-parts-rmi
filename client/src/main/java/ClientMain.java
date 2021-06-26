import controller.CommandController;
import stubs.Part;
import stubs.PartRepository;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientMain {
    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);

            System.out.println("Digite o nome do servidor que gostaria de conectar: ");
            String inputName = sc.nextLine();

            System.out.println("Digite a porta do servidor que gostaria de conectar: ");
            String inputPort = sc.nextLine();

            StringBuilder serverName = new StringBuilder("//127.0.0.1:");
            serverName.append(inputPort).append("/").append(inputName);
            System.out.println(serverName.toString());

            CommandController controller = new CommandController(sc, serverName.toString());

            String options = "\nbind | listp | getp | showp | clearlist | addsubpart | addp | quit";

            System.out.println("Conectado ao servidor " + inputName + ". Digite uma das opcoes abaixo:");
            System.out.println(options);
            String input = sc.nextLine();

            while (!input.startsWith("quit")) {

                switch (input) {
                    case "addp":
                        controller.newPartCommand();
                        break;

                    case "listp":
                        List<Part> parts = controller.listParts();
                        StringBuilder sb = new StringBuilder("");
                        for (Part part : parts) {
                            sb.append(part.toString()).append("\n");
                        }
                        System.out.println(sb);
                        break;

                    case "getp":
                        System.out.print("Insira o id que deseja buscar: ");

                        try {
                            UUID searchId = UUID.fromString(sc.nextLine());
                            Part part = controller.findPart(searchId);
                            if (part == null) {
                                System.out.println("Part inexistente");
                            } else {
                                controller.setCurrentPart(part);
                                System.out.println("Part encontrada: " + part);
                            }
                        }
                        catch(Exception e){
                            System.out.println("Input invalido");
                        }
                        break;

                    case "addsubpart":

                        System.out.println("Digite a quantidade de unidades da peca corrente: ");
                        int quant = Integer.parseInt(sc.nextLine());

                        controller.addSubpartCommand(quant);
                        System.out.println(controller.getCurrentSubParts().get(controller.getCurrentPart().getId()));
                        break;

                    case "bind":

                        String tempName = inputName;
                        String tempPort = inputPort;

                        System.out.println("Digite o nome do novo servidor: ");
                        inputName = sc.nextLine();

                        System.out.println("Digite a porta do novo servidor: ");
                        inputPort = sc.nextLine();

                        serverName.delete(serverName.length() - tempName.length() - tempPort.length() - 1, serverName.length());
                        serverName.append(inputPort).append("/").append(inputName);

                        controller.changeServer(serverName.toString());
                        break;

                    case "showp":

                        Part part = controller.getCurrentPart();

                        System.out.println(part);
                        System.out.println("Subparts: ");

                        for(UUID id : part.getSubcomponents().keySet()){
                            System.out.println(controller.findPart(id) + " quantity=" + part.getSubcomponents().get(id));
                        }

                        break;

                    case "clearlist":
                        System.out.println("Limpando lista");
                        controller.clearListCommand();
                        break;

                    case "subp":
                        for(UUID id : controller.getCurrentSubParts().keySet()){
                            System.out.println(controller.findPart(id));
                        }
                        break;

                    default:
                        System.out.println("Comando invalido");
                }

                System.out.println(options);
                input = sc.nextLine();
            }

        } catch (Exception e) {
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }
}
