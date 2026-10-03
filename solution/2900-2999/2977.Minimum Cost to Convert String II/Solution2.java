class Node {
    Node[] children = new Node[26];
    int v = -1;
}

class Solution {
    private final long inf = 1L << 60;
    private Node root = new Node();
    private int idx;

    private long[][] g;

    public long minimumCost(
        String source, String target, String[] original, String[] changed, int[] cost) {
        int m = cost.length;
        g = new long[m << 1][m << 1];
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
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
        int n = s.length;
        long[] f = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            f[i] = inf;
        }
        for (int i = n - 1; i >= 0; --i) {
            long res = s[i] == t[i] ? f[i + 1] : inf;
            Node p = root, q = root;
            for (int j = i; j < n; ++j) {
                int a = s[j] - 'a';
                int b = t[j] - 'a';
                if (p.children[a] == null || q.children[b] == null) {
                    break;
                }
                p = p.children[a];
                q = q.children[b];
                if (p.v < 0 || q.v < 0) {
                    continue;
                }
                long w = g[p.v][q.v];
                if (w < inf) {
                    res = Math.min(res, w + f[j + 1]);
                }
            }
            f[i] = res;
        }
        return f[0] >= inf ? -1 : f[0];
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
}
