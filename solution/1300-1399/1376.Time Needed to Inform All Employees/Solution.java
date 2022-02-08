class Solution {
    private Map<Integer, List<Integer>> g;
    private int[] manager;
    private int[] informTime;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        g = new HashMap<>();
        this.manager = manager;
        this.informTime = informTime;
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(manager[i], k -> new ArrayList<>()).add(i);
        }
        return dfs(headID);
    }

    private int dfs(int i) {
        int ans = 0;
        for (int j : g.getOrDefault(i, new ArrayList<>())) {
            ans = Math.max(ans, informTime[i] + dfs(j));
        }
        return ans;
    }
}