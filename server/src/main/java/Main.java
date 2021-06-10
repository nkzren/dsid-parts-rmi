import impl.PartRepositoryImpl;
import stubs.PartRepository;

import java.rmi.Naming;

public class Main {
    public static void main (String args [ ]) {
        //Cria e instala o security manager
        //System.setSecurityManager(new RMISecurityManager() );
        try {
            //Cria HelloImpl
            PartRepository obj = new PartRepositoryImpl();
            Naming.rebind("Tapioca", obj);
            System.out.println("Server pronto.");

        } catch(Exception e) {
            System.out.println("HelloServer erro"+ e.getMessage());
        }
    }
}
