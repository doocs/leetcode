class Solution {
    public long countStableSubarrays(int[] capacity) {
        int n = capacity.length;
        long[] s = new long[n + 1];
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + capacity[i - 1];
        }
        Map<Pair<Integer, Long>, Integer> cnt = new HashMap<>();
        long ans = 0;
        for (int r = 2; r < n; ++r) {
            int l = r - 2;
            cnt.merge(new Pair<>(capacity[l], capacity[l] + s[l + 1]), 1, Integer::sum);
            ans += cnt.getOrDefault(new Pair<>(capacity[r], s[r]), 0);
        }
        return ans;
    }
}
