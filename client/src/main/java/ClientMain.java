import controller.CommandController;
import stubs.Part;
import stubs.PartRepository;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.List;
import java.util.Scanner;

public class ClientMain {
    public static void main(String[] args) {
        try {

            PartRepository partRepository = (PartRepository) Naming.lookup("//127.0.0.1:10020/Main");

            Scanner sc = new Scanner(System.in);
            CommandController controller = new CommandController(sc, partRepository);

            System.out.println("Conectado ao servidor Main. Digite uma das opcoes abaixo:");
            System.out.println("[a]dicionar part | [l]istar parts | [p]rocurar part | [q]uit");
            String input = sc.nextLine();
            while (!"quit".equalsIgnoreCase(input)) {
                if (input.equals("a")) {
                    controller.newPartCommand();
                } else if (input.equals("l")){
                    List<Part> parts = controller.listParts();
                    StringBuilder sb = new StringBuilder("");
                    for (Part part : parts) {
                        sb.append(part.toString()).append(" ");
                    }
                    System.out.println(sb);
                }

                System.out.println("[a]dicionar part | [l]istar parts | [p]rocurar part | [q]uit");
                input = sc.nextLine();
            }

        } catch (Exception e) {
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }


}
