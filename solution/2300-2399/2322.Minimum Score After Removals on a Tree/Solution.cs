public class Solution {
    public int MinimumScore(int[] nums, int[][] edges) {
        int n = nums.Length;
        List<int>[] g = new List<int>[n];
        for (int i = 0; i < n; i++) {
            g[i] = new List<int>();
        }
        foreach (var e in edges) {
            int a = e[0], b = e[1];
            g[a].Add(b);
            g[b].Add(a);
        }

        int s = 0;
        foreach (int x in nums) {
            s ^= x;
        }

        int ans = int.MaxValue;
        int s1 = 0;

        int Dfs(int i, int fa) {
            int res = nums[i];
            foreach (int j in g[i]) {
                if (j != fa) {
                    res ^= Dfs(j, i);
                }
            }
            return res;
        }

        int Dfs2(int i, int fa) {
            int res = nums[i];
            foreach (int j in g[i]) {
                if (j != fa) {
                    int s2 = Dfs2(j, i);
                    res ^= s2;
                    int mx = Math.Max(Math.Max(s ^ s1, s2), s1 ^ s2);
                    int mn = Math.Min(Math.Min(s ^ s1, s2), s1 ^ s2);
                    ans = Math.Min(ans, mx - mn);
                }
            }
            return res;
        }

        for (int i = 0; i < n; ++i) {
            foreach (int j in g[i]) {
                s1 = Dfs(i, j);
                Dfs2(i, j);
            }
        }

        return ans;
    }
}
