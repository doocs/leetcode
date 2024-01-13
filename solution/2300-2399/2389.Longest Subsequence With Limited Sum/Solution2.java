class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> queries[i] - queries[j]);
        int[] ans = new int[m];
        int s = 0, j = 0;
        for (int i : idx) {
            while (j < nums.length && s + nums[j] <= queries[i]) {
                s += nums[j++];
            }
            ans[i] = j;
        }
        return ans;
    }
}