import stubs.PartRepository;

import java.rmi.Naming;

public class ClientMain {
    public static void main(String[] args) {
        try {
            PartRepository partRepository = (PartRepository) Naming.lookup("//127.0.0.1:10020/Main");
            System.out.println("Teste: " + partRepository.getPartList());
        } catch (Exception e) {
            System.out.println("Deu ruim");
            e.printStackTrace();
        }
    }
}
