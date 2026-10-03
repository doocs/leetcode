class Trie {
    public final int inf = 1 << 29;
    public Trie[] children = new Trie[26];
    public int cost = inf;

    public void insert(String word, int cost) {
        Trie node = this;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                node.children[idx] = new Trie();
            }
            node = node.children[idx];
        }
        node.cost = Math.min(node.cost, cost);
    }
}

class Solution {
    public int minimumCost(String target, String[] words, int[] costs) {
        Trie trie = new Trie();
        for (int i = 0; i < words.length; ++i) {
            trie.insert(words[i], costs[i]);
        }
        int n = target.length();
        int inf = trie.inf;
        int[] f = new int[n + 1];
        Arrays.fill(f, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            Trie node = trie;
            for (int j = i; j < n; ++j) {
                int idx = target.charAt(j) - 'a';
                if (node.children[idx] == null) {
                    break;
                }
                node = node.children[idx];
                f[i] = Math.min(f[i], node.cost + f[j + 1]);
            }
        }
        return f[0] < inf ? f[0] : -1;
    }
}
