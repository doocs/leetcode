class Solution {
    public long maxSum(int[] nums, int length, int k) {
        int n = nums.length;
        int maxSwaps = Math.min(k, Math.min(length, n - length));
        long ans = Long.MIN_VALUE;
        for (int i = 0; i <= n - length; ++i) {
            int[] internal = Arrays.copyOfRange(nums, i, i + length);
            int[] external = new int[n - length];
            System.arraycopy(nums, 0, external, 0, i);
            System.arraycopy(nums, i + length, external, i, n - length - i);
            Arrays.sort(internal);
            Arrays.sort(external);
            long total = 0;
            for (int v : internal) {
                total += v;
            }
            for (int j = 0; j < maxSwaps; ++j) {
                long gain = (long) external[external.length - 1 - j] - internal[j];
                if (gain <= 0) {
                    break;
                }
                total += gain;
            }
            ans = Math.max(ans, total);
        }
        return ans;
    }
}
