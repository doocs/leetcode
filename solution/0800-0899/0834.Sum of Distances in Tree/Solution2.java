class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (var e : edges) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }
        int[] ans = new int[n];
        int[] size = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0, 0});
        while (!stk.isEmpty()) {
            int[] f = stk.pop();
            int i = f[0], fa = f[1], d = f[2], state = f[3];
            if (state == 0) {
                ans[0] += d;
                stk.push(new int[] {i, fa, d, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, d + 1, 0});
                    }
                }
            } else {
                size[i] = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        size[i] += size[j];
                    }
                }
            }
        }
        Deque<int[]> walk = new ArrayDeque<>();
        walk.push(new int[] {0, -1, ans[0]});
        while (!walk.isEmpty()) {
            int[] f = walk.pop();
            int i = f[0], fa = f[1], t = f[2];
            ans[i] = t;
            for (int j : g[i]) {
                if (j != fa) {
                    walk.push(new int[] {j, i, t - size[j] + n - size[j]});
                }
            }
        }
        return ans;
    }
}
