class Trie {
    Trie[] children = new Trie[26];
    String v;

    void insert(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
        }
        node.v = word;
    }

    String search(String word) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return word;
            }
            node = node.children[c];
            if (node.v != null) {
                return node.v;
            }
        }
        return word;
    }
}

class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String v : dictionary) {
            trie.insert(v);
        }
        List<String> ans = new ArrayList<>();
        for (String v : sentence.split("\\s")) {
            ans.add(trie.search(v));
        }
        return String.join(" ", ans);
    }
}