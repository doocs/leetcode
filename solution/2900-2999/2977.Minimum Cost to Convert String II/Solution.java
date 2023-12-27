class Node {
    Node[] children = new Node[26];
    int v = -1;
}

class Solution {
    private final long inf = 1L << 60;
    private Node root = new Node();
    private int idx;

    private long[][] g;
    private char[] s;
    private char[] t;
    private Long[] f;

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        int m = cost.length;
        g = new long[m << 1][m << 1];
        s = source.toCharArray();
        t = target.toCharArray();
        for (int i = 0; i < g.length; ++i) {
            Arrays.fill(g[i], inf);
            g[i][i] = 0;
        }
        for (int i = 0; i < m; ++i) {
            int x = insert(original[i]);
            int y = insert(changed[i]);
            g[x][y] = Math.min(g[x][y], cost[i]);
        }
        for (int k = 0; k < idx; ++k) {
            for (int i = 0; i < idx; ++i) {
                if (g[i][k] >= inf) {
                    continue;
                }
                for (int j = 0; j < idx; ++j) {
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        f = new Long[s.length];
        long ans = dfs(0);
        return ans >= inf ? -1 : ans;
    }

    private int insert(String w) {
        Node node = root;
        for (char c : w.toCharArray()) {
            int i = c - 'a';
            if (node.children[i] == null) {
                node.children[i] = new Node();
            }
            node = node.children[i];
        }
        if (node.v < 0) {
            node.v = idx++;
        }
        return node.v;
    }

    private long dfs(int i) {
        if (i >= s.length) {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        long res = s[i] == t[i] ? dfs(i + 1) : inf;
        Node p = root, q = root;
        for (int j = i; j < s.length; ++j) {
            p = p.children[s[j] - 'a'];
            q = q.children[t[j] - 'a'];
            if (p == null || q == null) {
                break;
            }
            if (p.v < 0 || q.v < 0) {
                continue;
            }
            long t = g[p.v][q.v];
            if (t < inf) {
                res = Math.min(res, t + dfs(j + 1));
            }
        }
        return f[i] = res;
    }
}