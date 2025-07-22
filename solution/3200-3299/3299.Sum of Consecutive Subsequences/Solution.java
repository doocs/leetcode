class Solution {
    private final int mod = (int) 1e9 + 7;

    public int getSum(int[] nums) {
        long x = calc(nums);
        for (int i = 0, j = nums.length - 1; i < j; ++i, --j) {
            int t = nums[i];
            nums[i] = nums[j];
            nums[j] = t;
        }
        long y = calc(nums);
        long s = Arrays.stream(nums).asLongStream().sum();
        return (int) ((x + y + s) % mod);
    }

    private long calc(int[] nums) {
        int n = nums.length;
        long[] left = new long[n];
        long[] right = new long[n];
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            cnt.merge(nums[i - 1], 1 + cnt.getOrDefault(nums[i - 1] - 1, 0L), Long::sum);
            left[i] = cnt.getOrDefault(nums[i] - 1, 0L);
        }
        cnt.clear();
        for (int i = n - 2; i >= 0; --i) {
            cnt.merge(nums[i + 1], 1 + cnt.getOrDefault(nums[i + 1] + 1, 0L), Long::sum);
            right[i] = cnt.getOrDefault(nums[i] + 1, 0L);
        }
        long ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = (ans + (left[i] + right[i] + left[i] * right[i] % mod) * nums[i] % mod) % mod;
        }
        return ans;
    }
}
