class Trie {
    private Trie[] children = new Trie[26];
    private int cnt;

    public void insert(String w) {
        Trie node = this;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                node.children[c] = new Trie();
            }
            node = node.children[c];
            ++node.cnt;
        }
    }

    public int search(String w) {
        Trie node = this;
        int ans = 0;
        for (char c : w.toCharArray()) {
            c -= 'a';
            if (node.children[c] == null) {
                return ans;
            }
            node = node.children[c];
            ans += node.cnt;
        }
        return ans;
    }
}

class Solution {
    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int[] ans = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            ans[i] = trie.search(words[i]);
        }
        return ans;
    }
}