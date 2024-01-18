public class Solution {
    private List<int>[] g;
    private int[] informTime;

    public int NumOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        this.informTime = informTime;
        for (int i = 0; i < n; ++i) {
            if (manager[i] != -1) {
                g[manager[i]].Add(i);
            }
        }
        return dfs(headID);
    }

    private int dfs(int i) {
        int ans = 0;
        foreach (int j in g[i]) {
            ans = Math.Max(ans, dfs(j) + informTime[i]);
        }
        return ans;
    }
}
