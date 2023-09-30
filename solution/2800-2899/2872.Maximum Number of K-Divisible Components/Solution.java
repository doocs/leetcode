class Solution {
    int n, k;
    int[] values;
    int[] dfs(int curr, int parent, List<List<Integer>> adj) {
        int[] res = new int[] {0, values[curr] % k};
        for (int next : adj.get(curr)) {
            if (next == parent) {
                continue;
            }
            int[] update = dfs(next, curr, adj);
            res[0] += update[0];
            res[1] += update[1];
        }
        res[1] %= k;
        res[0] += res[1] == 0 ? 1 : 0;
        return res;
    }
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        this.n = n;
        this.k = k;
        this.values = values;
        List<List<Integer>> adj = new ArrayList<>();
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        int[] ans = dfs(0, -1, adj);
        return ans[1] == 0 ? ans[0] : 0;
    }
}