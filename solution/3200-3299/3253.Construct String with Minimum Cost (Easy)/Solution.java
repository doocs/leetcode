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
    private Trie trie = new Trie();
    private char[] target;
    private Integer[] f;

    public int minimumCost(String target, String[] words, int[] costs) {
        for (int i = 0; i < words.length; ++i) {
            trie.insert(words[i], costs[i]);
        }
        this.target = target.toCharArray();
        f = new Integer[target.length()];
        int ans = dfs(0);
        return ans < trie.inf ? ans : -1;
    }

    private int dfs(int i) {
        if (i >= target.length) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        f[i] = trie.inf;
        Trie node = trie;
        for (int j = i; j < target.length; ++j) {
            int idx = target[j] - 'a';
            if (node.children[idx] == null) {
                return f[i];
            }
            node = node.children[idx];
            f[i] = Math.min(f[i], node.cost + dfs(j + 1));
        }
        return f[i];
    }
}
