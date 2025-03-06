public class Solution {
    public int[] SumEvenAfterQueries(int[] nums, int[][] queries) {
        int s = nums.Where(x => x % 2 == 0).Sum();
        int[] ans = new int[queries.Length];

        for (int j = 0; j < queries.Length; j++) {
            int v = queries[j][0], i = queries[j][1];
            if (nums[i] % 2 == 0) {
                s -= nums[i];
            }
            nums[i] += v;
            if (nums[i] % 2 == 0) {
                s += nums[i];
            }
            ans[j] = s;
        }

        return ans;
    }
}
