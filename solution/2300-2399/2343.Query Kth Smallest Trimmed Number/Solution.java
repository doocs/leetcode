class Solution {
    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = queries.length;
        int m = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int k = queries[i][0], t = queries[i][1];
            String[][] arr = new String[m][2];
            for (int j = 0; j < m; ++j) {
                arr[j][0] = nums[j].substring(nums[j].length() - t);
                arr[j][1] = String.valueOf(j);
            }
            Arrays.sort(arr, (a, b) -> {
                int x = a[0].compareTo(b[0]);
                return x == 0 ? Long.compare(Integer.valueOf(a[1]), Integer.valueOf(b[1])) : x;
            });
            ans[i] = Integer.valueOf(arr[k - 1][1]);
        }
        return ans;
    }
}