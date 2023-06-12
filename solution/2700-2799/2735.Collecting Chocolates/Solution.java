class Solution {
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        int[][] min = new int[n][n];
        for (int i = 0; i < n; i++) {
            int w = 0x3f3f3f3f;
            for (int j = i; j < n; j++) {
                w = Math.min(w, nums[j]);
                min[i][j] = w;
            }
        }
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                int l = j - i;
                if (l < 0) {
                    sum += Math.min(min[0][j], min[n + l][n - 1]);
                } else {
                    sum += min[l][j];
                }
            }
            res = Math.min(res, sum + x * 1L * i);
        }
        return res;
    }
}