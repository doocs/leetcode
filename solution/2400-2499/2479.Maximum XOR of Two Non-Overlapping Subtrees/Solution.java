class Trie {
    Trie[] children = new Trie[2];

    void insert(long x) {
        Trie node = this;
        for (int i = 47; i >= 0; --i) {
            int v = (int) (x >> i) & 1;
            if (node.children[v] == null) {
                node.children[v] = new Trie();
            }
            node = node.children[v];
        }
    }

    long search(long x) {
        Trie node = this;
        long res = 0;
        for (int i = 47; i >= 0; --i) {
            int v = (int) (x >> i) & 1;
            if (node == null) {
                return res;
            }
            if (node.children[v ^ 1] != null) {
                res = res << 1 | 1;
                node = node.children[v ^ 1];
            } else {
                res <<= 1;
                node = node.children[v];
            }
        }
        return res;
    }
}

class Solution {
    private List<Integer>[] g;
    private int[] vals;
    private long[] s;
    private Trie tree;
    private long ans;

    public long maxXor(int n, int[][] edges, int[] values) {
        g = new List[n];
        s = new long[n];
        vals = values;
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs1(0, -1);
        tree = new Trie();
        dfs2(0, -1);
        return ans;
    }

    private void dfs2(int i, int fa) {
        ans = Math.max(ans, tree.search(s[i]));
        for (int j : g[i]) {
            if (j != fa) {
                dfs2(j, i);
            }
        }
        tree.insert(s[i]);
    }

    private long dfs1(int i, int fa) {
        long t = vals[i];
        for (int j : g[i]) {
            if (j != fa) {
                t += dfs1(j, i);
            }
        }
        s[i] = t;
        return t;
    }
}