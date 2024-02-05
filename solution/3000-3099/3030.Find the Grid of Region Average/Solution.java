class Solution {
    public int[][] resultGrid(int[][] image, int threshold) {
        int n = image.length;
        int m = image[0].length;
        int[][] ans = new int[n][m];
        int[][] ct = new int[n][m];
        for (int i = 0; i + 2 < n; ++i) {
            for (int j = 0; j + 2 < m; ++j) {
                boolean region = true;
                for (int k = 0; k < 3; ++k) {
                    for (int l = 0; l < 2; ++l) {
                        region &= Math.abs(image[i + k][j + l] - image[i + k][j + l + 1]) <= threshold;
                    }
                }
                for (int k = 0; k < 2; ++k) {
                    for (int l = 0; l < 3; ++l) {
                        region &= Math.abs(image[i + k][j + l] - image[i + k + 1][j + l]) <= threshold;
                    }
                }
                if (region) {
                    int tot = 0;
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            tot += image[i + k][j + l];
                        }
                    }
                    for (int k = 0; k < 3; ++k) {
                        for (int l = 0; l < 3; ++l) {
                            ct[i + k][j + l]++;
                            ans[i + k][j + l] += tot / 9;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (ct[i][j] == 0) {
                    ans[i][j] = image[i][j];
                } else {
                    ans[i][j] /= ct[i][j];
                }
            }
        }
        return ans;
    }
}
