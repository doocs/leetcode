class Solution {
    private Map<List<Integer>, Integer> f = new HashMap<>();
    private String s;
    private int k;

    public int maxPartitionsAfterOperations(String s, int k) {
        this.s = s;
        this.k = k;
        return dfs(0, 0, 1);
    }

    private int dfs(int i, int cur, int t) {
        if (i >= s.length()) {
            return 1;
        }
        var key = List.of(i, cur, t);
        if (f.containsKey(key)) {
            return f.get(key);
        }
        int v = 1 << (s.charAt(i) - 'a');
        int nxt = cur | v;
        int ans = Integer.bitCount(nxt) > k ? dfs(i + 1, v, t) + 1 : dfs(i + 1, nxt, t);
        if (t > 0) {
            for (int j = 0; j < 26; ++j) {
                nxt = cur | (1 << j);
                if (Integer.bitCount(nxt) > k) {
                    ans = Math.max(ans, dfs(i + 1, 1 << j, 0) + 1);
                } else {
                    ans = Math.max(ans, dfs(i + 1, nxt, 0));
                }
            }
        }
        f.put(key, ans);
        return ans;
    }
}