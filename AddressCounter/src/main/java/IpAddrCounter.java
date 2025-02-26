import java.io.BufferedReader;
import java.io.FileReader;

public class IpAddrCounter {


    //we use as bigger the size as possible along with number of hash functions to minimise the bloom filter Probability of false positives cases

    private static final int SIZE = (Integer.MAX_VALUE / 2) + 1;

    public static void main(String[] args) {

        BloomFilter bloomFilter = new BloomFilter(SIZE);
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(IpAddrCounter.class.getResource("ListOfIps.txt").getFile()));
            String ipAddress;
            long numberOfDifferentAddreses = 0;
            while ((ipAddress = bufferReader.readLine()) != null) {
                if (!bloomFilter.mightContain(ipAddress)) {
                    bloomFilter.add(ipAddress);
                    numberOfDifferentAddreses++;
                }
            }
            System.out.println(numberOfDifferentAddreses);
        } catch (Exception e) {
            System.out.println("Where was some issue during reading the file");
            e.printStackTrace();
        }

    }
}