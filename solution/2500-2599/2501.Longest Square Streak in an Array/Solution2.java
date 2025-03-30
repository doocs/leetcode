class Solution {
    private Map<Long, Integer> f = new HashMap<>();
    private Set<Long> s = new HashSet<>();

    public int longestSquareStreak(int[] nums) {
        for (long x : nums) {
            s.add(x);
        }
        int ans = 0;
        for (long x : s) {
            ans = Math.max(ans, dfs(x));
        }
        return ans < 2 ? -1 : ans;
    }

    private int dfs(long x) {
        if (!s.contains(x)) {
            return 0;
        }
        if (f.containsKey(x)) {
            return f.get(x);
        }
        int ans = 1 + dfs(x * x);
        f.put(x, ans);
        return ans;
    }
}