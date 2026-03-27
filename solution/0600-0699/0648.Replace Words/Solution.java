class Trie {
    Trie[] children = new Trie[26];
    boolean isEnd = false;

    void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.isEnd = true;
    }

    String search(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); i++) {
            int idx = w.charAt(i) - 'a';
            if (node.children[idx] == null) {
                return w;
            }
            node = node.children[idx];
            if (node.isEnd) {
                return w.substring(0, i + 1);
            }
        }
        return w;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String w : dictionary) {
            trie.insert(w);
        }

        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = trie.search(words[i]);
        }
        return String.join(" ", words);
    }
}
