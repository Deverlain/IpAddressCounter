public class Trie {
    //in ip adress we have '0'-'9' and '.' as possible chars
    private static final int NUMBER_OF_CHARS = 11;

    class TrieNode {
        TrieNode[] children;
        boolean isEndOfAddress;

        public TrieNode() {
            children = new TrieNode[NUMBER_OF_CHARS];
            isEndOfAddress = false;
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String address) {
        TrieNode current = root;
        for (int i = 0; i < address.length(); i++) {
            int charIndex = getIndexFromChar(address.charAt(i));
            if (current.children[charIndex] == null) {
                current.children[charIndex] = new TrieNode();
            }
            current = current.children[charIndex];
        }
        current.isEndOfAddress = true;
    }


    public boolean contains(String address) {
        TrieNode current = root;
        for (int i = 0; i < address.length(); i++) {
            int charIndex = getIndexFromChar(address.charAt(i));
            if (current.children[charIndex] == null) {
                return false;
            }
            current = current.children[charIndex];
        }
        return current != null && current.isEndOfAddress;
    }

    private int getIndexFromChar(char ch) {
        int charIndex;
        if ((ch == '.')) {
            charIndex = 10;
        } else {
            charIndex = ch - '0';
        }
        return charIndex;
    }

}