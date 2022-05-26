class Solution {
    public int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int s = 0;
        for (int num : nums) {
            if (num % 2 == 0) {
                s += num;
            }
        }
        int[] ans = new int[queries.length];
        int idx = 0;
        for (int[] q : queries) {
            int v = q[0], i = q[1];
            int old = nums[i];
            nums[i] += v;
            if (nums[i] % 2 == 0 && old % 2 == 0) {
                s += v;
            } else if (nums[i] % 2 == 0 && old % 2 != 0) {
                s += nums[i];
            } else if (old % 2 == 0) {
                s -= old;
            }
            ans[idx++] = s;
        }
        return ans;
    }
}