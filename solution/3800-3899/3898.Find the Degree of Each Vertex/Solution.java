class Solution {
    public int[] findDegrees(int[][] matrix) {
        int n = matrix.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int x : matrix[i]) {
                ans[i] += x;
            }
        }
        return ans;
    }
}
