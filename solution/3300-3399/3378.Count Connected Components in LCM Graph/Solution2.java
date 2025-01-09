class Solution {
    private void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        if (visited[node]) return;
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            dfs(neighbor, adj, visited);
        }
    }

    public int countComponents(int[] nums, int threshold) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= threshold; i++) {
            adj.add(new ArrayList<>());
        }
        boolean[] visited = new boolean[threshold + 1];
        int ans = 0;

        for (int num : nums) {
            if (num > threshold) {
                ans++;
                continue;
            }
            for (int j = 2 * num; j <= threshold; j += num) {
                adj.get(num).add(j);
                adj.get(j).add(num);
            }
        }

        for (int num : nums) {
            if (num <= threshold && !visited[num]) {
                dfs(num, adj, visited);
                ans++;
            }
        }

        return ans;
    }
}
