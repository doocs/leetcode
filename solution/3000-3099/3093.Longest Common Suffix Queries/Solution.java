class Trie {
    private final int inf = 1 << 30;
    private Trie[] children = new Trie[26];
    private int length = inf;
    private int idx = inf;

    public void insert(String w, int i) {
        Trie node = this;
        if (node.length > w.length()) {
            node.length = w.length();
            node.idx = i;
        }
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
            if (node.length > w.length()) {
                node.length = w.length();
                node.idx = i;
            }
        }
    }

    public int query(String w) {
        Trie node = this;
        for (int k = w.length() - 1; k >= 0; --k) {
            int idx = w.charAt(k) - 'a';
            if (node.children[idx] == null) {
                break;
            }
            node = node.children[idx];
        }
        return node.idx;
    }
}

class Solution {
    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        Trie trie = new Trie();
        for (int i = 0; i < wordsContainer.length; ++i) {
            trie.insert(wordsContainer[i], i);
        }
        int n = wordsQuery.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = trie.query(wordsQuery[i]);
        }
        return ans;
    }
}