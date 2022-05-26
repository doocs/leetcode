class Solution {
    // https://oi-wiki.org/ds/dsu/#_3
    private boolean[] used;

    public int maxNumEdgesToRemove(int n, int[][] edges) {
        used = new boolean[edges.length];
        // type 为倒序，3, 2, 1
        Arrays.sort(edges, (a, b) -> Integer.compare(b[0], a[0]));
        if (!unionFind(n, edges, 1)) return -1;
        if (!unionFind(n, edges, 2)) return -1;
        int result = 0;
        for (boolean u : used) {
            result += u ? 0 : 1;
        }
        return result;
    }

    private boolean unionFind(int n, int[][] edges, int excludedType) {
        int[] union = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            union[i] = i;
        }
        int cnt = 0;
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge[0] == excludedType) continue;
            int rootA = findRoot(union, edge[1]);
            int rootB = findRoot(union, edge[2]);
            if (rootA != rootB) {
                cnt += 1;
                union[rootA] = rootB;
                used[i] = true;
            }
            if (cnt == n - 1) return true;
        }
        return false;
    }

    private int findRoot(int[] union, int idx) {
        if (union[idx] != idx) {
            int root = findRoot(union, union[idx]);
            union[idx] = root;
            return root;
        }
        return idx;
    }
}