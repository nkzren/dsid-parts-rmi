import impl.PartRepositoryImpl;
import stubs.PartRepository;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    private static Registry registry;

    public static void main (String args [ ]) {
        //Cria e instacd la o security manager
        //System.setSecurityManager(new RMISecurityManager() );
        try {
            //Cria HelloImpl
            LocateRegistry.createRegistry(10020);
            registry = LocateRegistry.getRegistry(10020);
            PartRepository obj = new PartRepositoryImpl();
            registry.bind("Main", obj);
            System.out.println("Server pronto.");

        } catch(Exception e) {
            System.out.println("HelloServer erro"+ e.getMessage());
        }
    }
}
