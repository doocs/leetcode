class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; ++i) {
            nums[i] += nums[i - 1];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = Arrays.binarySearch(nums, queries[i] + 1);
            ans[i] = j < 0 ? -j - 1 : j;
        }
        return ans;
    }
}