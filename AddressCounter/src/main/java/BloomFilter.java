import java.util.BitSet;
import java.util.function.Function;

public class BloomFilter {

    //we use many different hash functions along with size number of bites to minimise probability of false positives cases
    private final Function<String, Integer>[] HASH_FUNCTIONS = new Function[]{
            Object::hashCode,
            this::sascii,
            this::sfold

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

    public boolean mightContain(String element) {
        for (Function<String, Integer> hashFunction : HASH_FUNCTIONS) {
            int hash = hashFunction.apply(element);
            if (!bitSet.get(Math.abs(hash) % bitSet.size())) {
                return false;
            }
        }
        return true;
    }

    private int sascii(Object o) {
        String s = o.toString();
        char[] chars = s.toCharArray();

        int i, sum;
        for (sum = 0, i = 0; i < s.length(); i++) {
            sum += chars[i];
        }
        return Math.abs(sum % (Integer.MAX_VALUE / 2));
    }

    private int sfold(Object o) {
        String s = o.toString();
        long sum = 0, mul = 1;
        for (int i = 0; i < s.length(); i++) {
            mul = (i % 4 == 0) ? 1 : mul * 256;
            sum += s.charAt(i) * mul;
        }
        return (int) (Math.abs(sum) % (Integer.MAX_VALUE / 2));
    }
}