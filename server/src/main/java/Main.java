import impl.PartRepositoryImpl;
import stubs.PartRepository;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main (String args [ ]) {
        //Cria e instacd la o security manager
        //System.setSecurityManager(new RMISecurityManager() );
        try {
            //Cria HelloImpl
            LocateRegistry.createRegistry(1020);
            PartRepository obj = new PartRepositoryImpl();
            Naming.rebind("rmi://127.0.0.1:1020/Main", obj);
            System.out.println("Server pronto.");

        } catch(Exception e) {
            System.out.println("HelloServer erro"+ e.getMessage());
        }
    }
}
