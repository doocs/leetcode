class Solution {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int n = 100010;
        int[] delta = new int[n];
        for (int[] request : requests) {
            ++delta[request[0]];
            --delta[request[1] + 1];
        }
        for (int i = 1; i < n; ++i) {
            delta[i] += delta[i - 1];
        }
        Arrays.sort(nums);
        Arrays.sort(delta);
        long res = 0;
        for (int i = n - 1, j = nums.length - 1; i >= 0 && delta[i] > 0; --i, --j) {
            res += (long) delta[i] * nums[j];
        }
        return (int) (res % 1000000007);
    }
}