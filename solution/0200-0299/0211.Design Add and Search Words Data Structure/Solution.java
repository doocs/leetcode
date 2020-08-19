class WordDictionary {

    class TrieNode {
        private TrieNode[] links;
        private boolean end;

        public TrieNode() {
            this.links = new TrieNode[26];
        }

        public boolean contains(char c) {
            return links[c - 'a'] != null;
        }

        public void put(char c, TrieNode trieNode) {
            links[c - 'a'] = trieNode;
        }

        public TrieNode get(char c) {
            return links[c - 'a'];
        }
    }

    private TrieNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.contains(c)) {
                node.put(c, new TrieNode());
            }
            node = node.get(c);
        }
        node.end = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchHelp(word, root);
    }

    private boolean searchHelp(String word, TrieNode root) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if ('.' == c) {
                for (int j = 0; j < node.links.length; j++) {
                    if (node.links[j] != null && searchHelp(word.substring(i + 1), node.links[j])) {
                        return true;
                    }
                }
                return false;
            }
            if (!node.contains(c)) {
                return false;
            }
            node = node.get(c);
        }
        return node != null && node.end;
    }
}