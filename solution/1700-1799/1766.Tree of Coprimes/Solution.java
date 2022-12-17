class Solution {
    private List<Integer>[] g;
    private List<Integer>[] f;
    private Deque<int[]>[] stks;
    private int[] nums;
    private int[] ans;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int u = e[0], v = e[1];
            g[u].add(v);
            g[v].add(u);
        }
        f = new List[51];
        stks = new Deque[51];
        Arrays.setAll(f, k -> new ArrayList<>());
        Arrays.setAll(stks, k -> new ArrayDeque<>());
        for (int i = 1; i < 51; ++i) {
            for (int j = 1; j < 51; ++j) {
                if (gcd(i, j) == 1) {
                    f[i].add(j);
                }
            }
        }
        this.nums = nums;
        ans = new int[n];
        dfs(0, -1, 0);
        return ans;
    }

    private void dfs(int i, int fa, int depth) {
        int t = -1, k = -1;
        for (int v : f[nums[i]]) {
            var stk = stks[v];
            if (!stk.isEmpty() && stk.peek()[1] > k) {
                t = stk.peek()[0];
                k = stk.peek()[1];
            }
        }
        ans[i] = t;
        for (int j : g[i]) {
            if (j != fa) {
                stks[nums[i]].push(new int[] {i, depth});
                dfs(j, i, depth + 1);
                stks[nums[i]].pop();
            }
        }
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}