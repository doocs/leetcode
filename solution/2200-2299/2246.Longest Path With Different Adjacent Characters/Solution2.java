class Solution {
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        int[] down = new int[n];
        int ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.push(new int[] {i, 1});
                for (int j : g[i]) {
                    stk.push(new int[] {j, 0});
                }
            } else {
                int mx = 0;
                for (int j : g[i]) {
                    int x = down[j] + 1;
                    if (s.charAt(i) != s.charAt(j)) {
                        ans = Math.max(ans, mx + x);
                        mx = Math.max(mx, x);
                    }
                }
                down[i] = mx;
            }
        }
        return ans + 1;
    }
}
