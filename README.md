# dsid-parts-rmi
Repositório do exercício programa 1 da disciplina ACH2147 - Desenvolvimento de Sistemas de Informação Distribuídos

# Pré-requisitos

 - JDK 8 instalada (**NÃO** utilizar versões superiores, elas não são compatíveis com a api de RMI)
 - Caso tenha mais de uma JDK, certifique-se de que a variável de ambiente `JAVA_HOME` está referenciando a pasta da JDK correta
 - Maven 3+ (testado na versão 3.8.1, mas qualquer versão a partir da 3 deve funcionar)

# Instalação

 - `mvn package` : Para compilar as classes nas respectivas pastas `target` do servidor e do cliente
 - `mvn rmic:rmic` : Para compilar os stubs gerados pelo servidor e copiá-los ao cliente

# Subindo o servidor

 - O jar gerado pelo maven fica no caminho `${ROOT}/server/target/server-1.0-SNAPSHOT.jar`, e pode ser movido para outro local
 - `java -jar server/target/server-1.0-SNAPSHOT.jar <nome_do_servidor> <porta>`
 - Para simular a situação de vários servidores, é possível subir mais de um servidor. 
   No entanto, não é possível conectá-lo na mesma porta, sendo necessário escolher uma porta diferente.
   O nome do servidor é arbitrário, e pode ser qualquer string alfanumérica (não testamos com strings muito grandes)
   
# Subindo o cliente

 - O jar gerado pelo maven fica no caminho `${ROOT}/client/target/client-1.0-SNAPSHOT.jar`, e pode ser movido para outro local
 - `java -jar client/target/client-1.0-SNAPSHOT.jar`
 - Seguir as instruções que são mostradas na tela do terminal