class Solution {
    public int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] vis = new boolean[n];
        int ans = -1;
        for (int i = 0; i < n; ++i) {
            if (vis[i]) {
                continue;
            }
            int curr = i;
            List<Integer> cycle = new ArrayList<>();
            while (curr != -1 && !vis[curr]) {
                cycle.add(curr);
                vis[curr] = true;
                curr = edges[curr];
            }
            if (curr == -1) {
                continue;
            }
            for (int j = 0; j < cycle.size(); ++j) {
                if (cycle.get(j) == curr) {
                    ans = Math.max(ans, cycle.size() - j);
                    break;
                }
            }
        }
        return ans;
    }
}