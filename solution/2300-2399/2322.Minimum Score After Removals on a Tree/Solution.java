class Solution {
    private int s;
    private int s1;
    private int n;
    private int ans = Integer.MAX_VALUE;
    private int[] nums;
    private List<Integer>[] g;

    public int minimumScore(int[] nums, int[][] edges) {
        n = nums.length;
        g = new List[n];
        this.nums = nums;
        for (int i = 0; i < n; ++i) {
            g[i] = new ArrayList<>();
        }
        for (int[] e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int v : nums) {
            s ^= v;
        }
        for (int i = 0; i < n; ++i) {
            for (int j : g[i]) {
                s1 = dfs(i, -1, j);
                dfs2(i, -1, j);
            }
        }
        return ans;
    }

    private int dfs(int i, int fa, int x) {
        int res = nums[i];
        for (int j : g[i]) {
            if (j != fa && j != x) {
                res ^= dfs(j, i, x);
            }
        }
        return res;
    }

    private int dfs2(int i, int fa, int x) {
        int res = nums[i];
        for (int j : g[i]) {
            if (j != fa && j != x) {
                int a = dfs2(j, i, x);
                res ^= a;
                int b = s1 ^ a;
                int c = s ^ s1;
                int t = Math.max(Math.max(a, b), c) - Math.min(Math.min(a, b), c);
                ans = Math.min(ans, t);
            }
        }
        return res;
    }
}