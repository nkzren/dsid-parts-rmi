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

            String options = "[a]dicionar part | [l]istar parts | [p]rocurar part | [i]nserir subpart | [t]rocar de servidor | [q]uit";

            System.out.println("Conectado ao servidor " + inputName + ". Digite uma das opcoes abaixo:");
            System.out.println(options);
            String input = sc.nextLine();

            while (!input.startsWith("q")) {

                if (input.startsWith("a")) {
                    controller.newPartCommand();
                }

                else if (input.startsWith("l")) {
                    List<Part> parts = controller.listParts();
                    StringBuilder sb = new StringBuilder("");
                    for (Part part : parts) {
                        sb.append(part.toString()).append("\n");
                    }
                    System.out.println(sb);
                }

                else if (input.startsWith("p")) {
                    System.out.print("Insira o id que deseja buscar: ");
                    UUID searchId = UUID.fromString(sc.nextLine());
                    Part part = controller.findPart(searchId);
                    if(part == null) {
                        System.out.println("Part inexistente");
                    }

                    else {
                        System.out.println("Part encontrada: " + part);
                    }
                }

                else if (input.startsWith("i")){
                    List<Part> parts = controller.listParts();
                    StringBuilder sb = new StringBuilder("");

                    for(Part part : parts){
                        sb.append("{Nome='").append(part.getName()).append("', id=").append(part.getId()).append("}\n");
                    }
                    System.out.println(sb);

                    System.out.println("Insira o id da part que deseja receber uma subpart: ");
                    UUID partId = UUID.fromString(sc.nextLine());

                    controller.addSubpartCommand(partId);
                }

                else if (input.startsWith("t")){

                    String tempName = inputName;
                    String tempPort = inputPort;

                    System.out.println("Digite o nome do novo servidor: ");
                    inputName = sc.nextLine();

                    System.out.println("Digite a porta do novo servidor: ");
                    inputPort = sc.nextLine();

                    serverName.delete(serverName.length() - tempName.length() - tempPort.length() - 1, serverName.length());
                    serverName.append(inputPort).append("/").append(inputName);

                    controller.changeServer(serverName.toString());
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
