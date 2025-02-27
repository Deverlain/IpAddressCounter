import java.io.BufferedReader;
import java.io.FileReader;

public class IpAddrCounter {


    //we use as bigger the size as possible along with number of hash functions to minimise the bloom filter probability of false positives cases
    private static final int SIZE = 256 * 256 * 256 * 128 - 1;

    public static void main(String[] args) {
        BloomFilter bloomFilter = new BloomFilter(SIZE);
        final long startTime = System.currentTimeMillis();
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(IpAddrCounter.class.getResource("ListOfIps.txt").getFile()));
            String ipAddress;
            long numberOfDifferentAddreses = 0;
            while ((ipAddress = bufferReader.readLine()) != null) {
                if (!bloomFilter.contain(ipAddress)) {
                    bloomFilter.add(ipAddress);
                    numberOfDifferentAddreses++;
                }
            }
            final long endTime = System.currentTimeMillis();
            System.out.println("Total number of different ip's: " + numberOfDifferentAddreses);
            System.out.println("Total execution time: " + (endTime - startTime) + "ms");
        } catch (Exception e) {
            System.out.println("Where was some issue during reading the file");
            e.printStackTrace();
        }

    }
}