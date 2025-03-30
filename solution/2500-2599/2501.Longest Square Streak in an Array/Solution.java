class Solution {
    public int longestSquareStreak(int[] nums) {
        Set<Long> s = new HashSet<>();
        for (long x : nums) {
            s.add(x);
        }
        int ans = -1;
        for (long x : s) {
            int t = 0;
            for (; s.contains(x); x *= x) {
                ++t;
            }
            if (t > 1) {
                ans = Math.max(ans, t);
            }
        }
        return ans;
    }
}