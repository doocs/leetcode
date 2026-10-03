class Solution {
    public int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parents[i]].add(i);
        }
        int ans = 0;
        long mx = 0;
        int[] sz = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            if (state == 0) {
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    if (j != fa) {
                        stk.push(new int[] {j, i, 0});
                    }
                }
            } else {
                int cnt = 1;
                long score = 1;
                for (int j : g[i]) {
                    if (j != fa) {
                        int t = sz[j];
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
                sz[i] = cnt;
            }
        }
        return ans;
    }
}
