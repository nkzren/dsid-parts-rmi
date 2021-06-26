import impl.PartRepositoryImpl;
import stubs.PartRepository;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static Registry registry;

    public static void main (String args []) {
        try {
            int port = Integer.parseInt(args[1]); // 10020
            LocateRegistry.createRegistry(port);
            registry = LocateRegistry.getRegistry(port);
            PartRepository obj = new PartRepositoryImpl();
            registry.bind(args[0], obj);
            System.out.println("Server pronto.");

        } catch(Exception e) {
            System.out.println("HelloServer erro"+ e.getMessage());
        }
    }
}
