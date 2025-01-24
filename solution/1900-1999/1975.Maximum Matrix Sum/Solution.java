class Solution {
    public long maxMatrixSum(int[][] matrix) {
        long s = 0;
        int mi = 1 << 30, cnt = 0;
        for (var row : matrix) {
            for (int x : row) {
                cnt += x < 0 ? 1 : 0;
                int y = Math.abs(x);
                mi = Math.min(mi, y);
                s += y;
            }
        }
        return cnt % 2 == 0 ? s : s - mi * 2;
    }
}
