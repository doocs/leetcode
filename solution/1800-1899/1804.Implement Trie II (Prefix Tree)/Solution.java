class Trie {
    private Trie[] children = new Trie[26];
    private int v;
    private int pv;

    public Trie() {
    }

    public void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            ++node.pv;
        }
        ++node.v;
    }

    public int countWordsEqualTo(String word) {
        Trie node = search(word);
        return node == null ? 0 : node.v;
    }

    public int countWordsStartingWith(String prefix) {
        Trie node = search(prefix);
        return node == null ? 0 : node.pv;
    }

    public void erase(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            node = node.children[c];
            --node.pv;
        }
        --node.v;
    }

    private Trie search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return null;
            }
            node = node.children[c];
        }
        return node;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * int param_2 = obj.countWordsEqualTo(word);
 * int param_3 = obj.countWordsStartingWith(prefix);
 * obj.erase(word);
 */