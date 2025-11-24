class Solution {
    public int maxBalancedSubarray(int[] nums) {
        Map<Long, Integer> d = new HashMap<>();
        int ans = 0;
        int a = 0, b = nums.length;
        d.put((long) b, -1);
        for (int i = 0; i < nums.length; ++i) {
            a ^= nums[i];
            b += nums[i] % 2 == 0 ? 1 : -1;
            long key = (1L * a << 32) | b;
            if (d.containsKey(key)) {
                ans = Math.max(ans, i - d.get(key));
            } else {
                d.put(key, i);
            }
        }
        return ans;
    }
}
