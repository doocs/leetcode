class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        List<Integer>[] f = new List[n];
        List<Integer>[] g = new List[n];
        Arrays.setAll(f, i -> new ArrayList<>());
        Arrays.setAll(g, i -> new ArrayList<>());
        for (var e : invocations) {
            int a = e[0], b = e[1];
            f[a].add(b);
            f[b].add(a);
            g[a].add(b);
        }
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(k);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            for (int j : g[i]) {
                if (!suspicious[j]) {
                    suspicious[j] = true;
                    stk.push(j);
                }
            }
        }
        boolean[] vis = new boolean[n];
        for (int i = 0; i < n; ++i) {
            if (suspicious[i] || vis[i]) {
                continue;
            }
            vis[i] = true;
            stk.push(i);
            while (!stk.isEmpty()) {
                int u = stk.pop();
                for (int j : f[u]) {
                    if (!vis[j]) {
                        suspicious[j] = false;
                        vis[j] = true;
                        stk.push(j);
                    }
                }
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (!suspicious[i]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
