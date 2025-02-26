import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class IpAddrCounter {

    private static Trie trie = new Trie();

    public static void main(String[] args) {
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(IpAddrCounter.class.getResource("ListOfIps.txt").getFile()));
            String ipAddress;
            BigInteger numberOfDifferentAddreses = BigInteger.ZERO;
            while ((ipAddress = bufferReader.readLine()) != null) {
                if (!trie.contains(ipAddress)) {
                    trie.insert(ipAddress);
                    numberOfDifferentAddreses = numberOfDifferentAddreses.add(BigInteger.ONE);
                }
            }
            System.out.println(numberOfDifferentAddreses);
        } catch (Exception e) {
            System.out.println("Where was some issue during reading the file");
            e.printStackTrace();
        }
    }
}