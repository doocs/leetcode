class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Long> p = new HashMap<>();
        long r = Long.MIN_VALUE;
        p.put(nums[0], 0L);
        long s = 0;
        int n = nums.length;
        for (int i = 0;; ++i) {
            s += nums[i];
            if (p.containsKey(nums[i] - k)) {
                r = Math.max(r, s - p.get(nums[i] - k));
            }
            if (p.containsKey(nums[i] + k)) {
                r = Math.max(r, s - p.get(nums[i] + k));
            }
            if (i + 1 == n)
            break;
            if (!p.containsKey(nums[i + 1]) || p.get(nums[i + 1]) > s) {
                p.put(nums[i + 1], s);
            }
        }
        return r == Long.MIN_VALUE ? 0 : r;
    }
}
