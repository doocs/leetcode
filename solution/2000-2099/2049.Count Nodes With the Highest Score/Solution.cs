public class Solution {
    private List<int>[] g;
    private int ans;
    private long mx;
    private int n;

    public int CountHighestScoreNodes(int[] parents) {
        n = parents.Length;
        g = new List<int>[n];
        for (int i = 0; i < n; ++i) {
            g[i] = new List<int>();
        }
        for (int i = 1; i < n; ++i) {
            g[parents[i]].Add(i);
        }
        
        Dfs(0, -1);
        return ans;
    }

    private int Dfs(int i, int fa) {
        int cnt = 1;
        long score = 1;
        
        foreach (int j in g[i]) {
            if (j != fa) {
                int t = Dfs(j, i);
                cnt += t;
                score *= t;
            }
        }
        
        if (n - cnt > 0) {
            score *= n - cnt;
        }
        
        if (mx < score) {
            mx = score;
            ans = 1;
        } else if (mx == score) {
            ++ans;
        }
        
        return cnt;
    }
}