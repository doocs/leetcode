public class Solution {
    public int[] AnswerQueries(int[] nums, int[] queries) {
        Array.Sort(nums);
        for (int i = 1; i < nums.Length; ++i) {
            nums[i] += nums[i - 1];
        }
        int m = queries.Length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = Array.BinarySearch(nums, queries[i] + 1);
            ans[i] = j < 0 ? -j - 1 : j;
        }
        return ans;
    }
}
