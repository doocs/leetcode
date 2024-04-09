public class TreeAncestor {
    private int[][] p;

    public TreeAncestor(int n, int[] parent) {
        p = new int[n][];
        for (int i = 0; i < n; i++) {
            p[i] = new int[18];
            for (int j = 0; j < 18; j++) {
                p[i][j] = -1;
            }
        }

        for (int i = 0; i < n; ++i) {
            p[i][0] = parent[i];
        }

        for (int j = 1; j < 18; ++j) {
            for (int i = 0; i < n; ++i) {
                if (p[i][j - 1] == -1) {
                    continue;
                }
                p[i][j] = p[p[i][j - 1]][j - 1];
            }
        }
    }

    public int GetKthAncestor(int node, int k) {
        for (int i = 17; i >= 0; --i) {
            if (((k >> i) & 1) == 1) {
                node = p[node][i];
                if (node == -1) {
                    break;
                }
            }
        }
        return node;
    }
}


/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.GetKthAncestor(node,k);
 */