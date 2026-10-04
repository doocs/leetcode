class Solution {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (manager[i] >= 0) {
                g[manager[i]].add(i);
            }
        }
        int[] time = new int[n];
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {headID, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], state = cur[1];
            if (state == 0) {
                stk.push(new int[] {i, 1});
                for (int j : g[i]) {
                    stk.push(new int[] {j, 0});
                }
            } else {
                int ans = 0;
                for (int j : g[i]) {
                    ans = Math.max(ans, time[j] + informTime[i]);
                }
                time[i] = ans;
            }
        }
        return time[headID];
    }
}
