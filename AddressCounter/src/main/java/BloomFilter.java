import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter {

    //we use many different hash functions along with size number of bites to minimise probability of false positives cases
    private final Function<String, Integer>[] HASH_FUNCTIONS = new Function[]{
            Object::hashCode,
            this::getAsciiHash,
            this::getStringFoldHash
    };

    private final BitSet bitSet;


    public BloomFilter(int size) {
        this.bitSet = new BitSet(size);
    }

    public void add(String element) {
        for (Function<String, Integer> hashFunction : HASH_FUNCTIONS) {
            int hash = hashFunction.apply(element);
            bitSet.set(Math.abs(hash) % bitSet.size(), true);
        }
    }

    public boolean contain(String element) {
        for (Function<String, Integer> hashFunction : HASH_FUNCTIONS) {
            int hash = hashFunction.apply(element);
            if (!bitSet.get(Math.abs(hash) % bitSet.size())) {
                return false;
            }
        }
        return true;
    }

    // ASCII hash function
    private int getAsciiHash(Object o) {
        String s = o.toString();
        char[] chars = s.toCharArray();
        int i, hashSum;
        for (hashSum = 0, i = 0; i < s.length(); i++) {
            hashSum += chars[i];
        }
        return Math.abs(hashSum % (Integer.MAX_VALUE / 2));
    }

    // String Folding hash function
    private int getStringFoldHash(Object o) {
        String s = o.toString();
        long hashSum = 0, mul = 1;
        for (int i = 0; i < s.length(); i++) {
            mul = (i % 4 == 0) ? 1 : mul * 256;
            hashSum += s.charAt(i) * mul;
        }
        return (int) (Math.abs(hashSum) % (Integer.MAX_VALUE / 2));
    }
}