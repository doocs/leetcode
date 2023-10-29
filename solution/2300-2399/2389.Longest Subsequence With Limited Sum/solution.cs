public class Solution {
    public int[] AnswerQueries(int[] nums, int[] queries) {
        int[] result = new int[queries.Length];
        Array.Sort(nums);
        for (int i = 0; i < queries.Length; i++) {
            result[i] = getSubsequent(nums, queries[i]);
        }
        return result;

    }

    public int getSubsequent(int[] nums,int query) {
        int sum = 0;
        for (int i = 0; i < nums.Length; i++) {
            sum += nums[i];
            if (sum > query) {
                return i;
            }
        }
        return nums.Length;
    }
}
