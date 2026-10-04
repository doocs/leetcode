class Solution {
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        List<Integer>[] g = new List[nodes];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < nodes; ++i) {
            g[parent[i]].add(i);
        }
        int[] sum = new int[nodes];
        int[] cnt = new int[nodes];
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
                int s = value[i], m = 1;
                for (int j : g[i]) {
                    s += sum[j];
                    m += cnt[j];
                }
                if (s == 0) {
                    m = 0;
                }
                sum[i] = s;
                cnt[i] = m;
            }
        }
        return cnt[0];
    }
}
