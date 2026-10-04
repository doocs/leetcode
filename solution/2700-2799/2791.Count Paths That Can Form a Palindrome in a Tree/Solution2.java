class Solution {
    public long countPalindromePaths(List<Integer> parent, String s) {
        int n = parent.size();
        List<int[]>[] g = new List[n];
        Arrays.setAll(g, k -> new ArrayList<>());
        for (int i = 1; i < n; ++i) {
            int p = parent.get(i);
            g[p].add(new int[] {i, 1 << (s.charAt(i) - 'a')});
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        Deque<int[]> stk = new ArrayDeque<>();
        stk.push(new int[] {0, 0});
        while (!stk.isEmpty()) {
            int[] cur = stk.pop();
            int i = cur[0], xor = cur[1];
            for (int[] e : g[i]) {
                int j = e[0], v = e[1];
                int x = xor ^ v;
                ans += cnt.getOrDefault(x, 0);
                for (int k = 0; k < 26; ++k) {
                    ans += cnt.getOrDefault(x ^ (1 << k), 0);
                }
                cnt.merge(x, 1, Integer::sum);
                stk.push(new int[] {j, x});
            }
        }
        return ans;
    }
}
