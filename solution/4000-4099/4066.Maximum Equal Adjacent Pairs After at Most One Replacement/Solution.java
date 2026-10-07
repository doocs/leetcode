class Solution {
    public int maxEqualAdjacentPairs(int[] nums) {
        Map<Long, Integer> cnt = new HashMap<>();
        int ans = 0, mx = 0;

        for (int i = 0; i + 1 < nums.length; i++) {
            int x = nums[i], y = nums[i + 1];
            if (x == y) {
                ans++;
            } else {
                if (x > y) {
                    int t = x;
                    x = y;
                    y = t;
                }
                long key = ((long) x << 30) | y;
                int v = cnt.merge(key, 1, Integer::sum);
                mx = Math.max(mx, v);
            }
        }
        ans += mx;
        return ans;
    }
}