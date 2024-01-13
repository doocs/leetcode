class Solution {
    private Map<Integer, Integer> f = new HashMap<>();
    private Set<Integer> s = new HashSet<>();

    public int longestSquareStreak(int[] nums) {
        for (int v : nums) {
            s.add(v);
        }
        int ans = 0;
        for (int v : nums) {
            ans = Math.max(ans, dfs(v));
        }
        return ans < 2 ? -1 : ans;
    }

    private int dfs(int x) {
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