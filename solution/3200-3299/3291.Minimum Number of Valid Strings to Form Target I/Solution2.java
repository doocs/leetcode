class Trie {
    Trie[] children = new Trie[26];

    void insert(String w) {
        Trie node = this;
        for (int i = 0; i < w.length(); ++i) {
            int j = w.charAt(i) - 'a';
            if (node.children[j] == null) {
                node.children[j] = new Trie();
            }
            node = node.children[j];
        }
    }
}

class Solution {
    public int minValidStrings(String[] words, String target) {
        Trie trie = new Trie();
        for (String w : words) {
            trie.insert(w);
        }
        int n = target.length();
        int inf = 1 << 30;
        int[] f = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            f[i] = inf;
        }
        char[] s = target.toCharArray();
        for (int i = n - 1; i >= 0; --i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int k = s[j] - 'a';
                if (node.children[k] == null) {
                    break;
                }
                node = node.children[k];
                f[i] = Math.min(f[i], 1 + f[j + 1]);
            }
        }
        return f[0] < inf ? f[0] : -1;
    }
}
