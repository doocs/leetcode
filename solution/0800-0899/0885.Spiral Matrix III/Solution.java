class Solution {
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int cnt = rows * cols;
        int[][] ans = new int[cnt][2];
        ans[0] = new int[]{rStart, cStart};
        if (cnt == 1) {
            return ans;
        }
        for (int k = 1, idx = 1;; k += 2) {
            int[][] dirs = new int[][]{{0, 1, k}, {1, 0, k}, {0, -1, k + 1}, {-1, 0, k + 1}};
            for (int[] dir : dirs) {
                int r = dir[0], c = dir[1], dk = dir[2];
                while (dk-- > 0) {
                    rStart += r;
                    cStart += c;
                    if (rStart >= 0 && rStart < rows && cStart >= 0 && cStart < cols) {
                        ans[idx++] = new int[]{rStart, cStart};
                        if (idx == cnt) {
                            return ans;
                        }
                    }
                }
            }
        }
    }
}