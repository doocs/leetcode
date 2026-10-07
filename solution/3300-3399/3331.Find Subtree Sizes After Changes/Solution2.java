class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = s.length();
        List<Integer>[] g = new List[n];
        List<Integer>[] d = new List[26];
        Arrays.setAll(g, k -> new ArrayList<>());
        Arrays.setAll(d, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            g[parent[i]].add(i);
        }
        int[] ans = new int[n];
        char[] cs = s.toCharArray();
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, -1, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], fa = cur[1], state = cur[2];
            int idx = cs[i] - 'a';
            if (state == 0) {
                ans[i] = 1;
                d[idx].add(i);
                stk.push(new int[] {i, fa, 1});
                for (int j : g[i]) {
                    stk.push(new int[] {j, i, 0});
                }
            } else {
                int k = d[idx].size() > 1 ? d[idx].get(d[idx].size() - 2) : fa;
                if (k >= 0) {
                    ans[k] += ans[i];
                }
                d[idx].remove(d[idx].size() - 1);
            }
        }
        return ans;
    }
}
