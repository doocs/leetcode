class Solution {
    public int minSizeSubarray(int[] nums, int target) {
        long s = Arrays.stream(nums).sum();
        int n = nums.length;
        int a = 0;
        if (target > s) {
            a = n * (target / (int) s);
            target -= target / s * s;
        }
        if (target == s) {
            return n;
        }
        Map<Long, Integer> pos = new HashMap<>();
        pos.put(0L, -1);
        long pre = 0;
        int b = 1 << 30;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            if (pos.containsKey(pre - target)) {
                b = Math.min(b, i - pos.get(pre - target));
            }
            if (pos.containsKey(pre - (s - target))) {
                b = Math.min(b, n - (i - pos.get(pre - (s - target))));
            }
            pos.put(pre, i);
        }
        return b == 1 << 30 ? -1 : a + b;
    }
}