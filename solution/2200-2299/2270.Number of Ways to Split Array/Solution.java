class Solution {
    public int waysToSplitArray(int[] nums) {
        long[] pre = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        int cnt = 0;
        for (int i = 1; i < nums.length; i++) {
            if (pre[i] >= pre[nums.length] - pre[i]) {
                cnt++;
            }
        }
        return cnt;
    }
}