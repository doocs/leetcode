class WordDictionary {
    class Trie {
        Trie[] children;
        boolean isEnd;
        Trie() {
            children = new Trie[26];
            isEnd = false;
        }
    }

    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }
    
    public void addWord(String word) {
        Trie node = trie;
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }
    
    public boolean search(String word) {
        return search(word, trie);
    }

    private boolean search(String word, Trie node) {
        for (int i = 0; i < word.length(); ++i) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (c != '.' && node.children[index] == null) {
                return false;
            }
            if (c == '.') {
                for (int j = 0; j < 26; ++j) {
                    if (node.children[j] != null && search(word.substring(i + 1), node.children[j])) {
                        return true;
                    }
                }
                return false;
            }
            node = node.children[index];
        }
        return node.isEnd;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */