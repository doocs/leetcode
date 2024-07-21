class Solution {
    public long minimumOperations(int[] nums, int[] target) {
        long f = Math.abs(target[0] - nums[0]);
        for (int i = 1; i < nums.length; ++i) {
            long x = target[i] - nums[i];
            long y = target[i - 1] - nums[i - 1];
            if (x * y > 0) {
                long d = Math.abs(x) - Math.abs(y);
                if (d > 0) {
                    f += d;
                }
            } else {
                f += Math.abs(x);
            }
        }
        return f;
    }
}