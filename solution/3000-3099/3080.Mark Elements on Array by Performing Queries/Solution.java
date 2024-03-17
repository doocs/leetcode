class Solution {
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        int n = nums.length;
        long s = Arrays.stream(nums).asLongStream().sum();
        boolean[] mark = new boolean[n];
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {nums[i], i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int m = queries.length;
        long[] ans = new long[m];
        for (int i = 0, j = 0; i < m; ++i) {
            int index = queries[i][0], k = queries[i][1];
            if (!mark[index]) {
                mark[index] = true;
                s -= nums[index];
            }
            for (; k > 0 && j < n; ++j) {
                if (!mark[arr[j][1]]) {
                    mark[arr[j][1]] = true;
                    s -= arr[j][0];
                    --k;
                }
            }
            ans[i] = s;
        }
        return ans;
    }
}