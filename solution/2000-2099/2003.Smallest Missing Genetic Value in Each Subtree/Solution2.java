class Solution {
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = nums.length;
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        int idx = -1;
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                g[parents[i]].add(i);
            }
            if (nums[i] == 1) {
                idx = i;
            }
        }
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        if (idx == -1) {
            return ans;
        }
        boolean[] vis = new boolean[n];
        boolean[] has = new boolean[n + 2];
        for (int i = 2; idx != -1; idx = parents[idx]) {
            dfs(g, vis, has, nums, idx);
            while (has[i]) {
                ++i;
            }
            ans[idx] = i;
        }
        return ans;
    }

    private void dfs(List<Integer>[] g, boolean[] vis, boolean[] has, int[] nums, int start) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(start);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            if (nums[i] < has.length) {
                has[nums[i]] = true;
            }
            for (int j : g[i]) {
                stk.push(j);
            }
        }
    }
}
