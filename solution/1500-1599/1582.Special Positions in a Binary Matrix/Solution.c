int numSpecial(int** mat, int matSize, int* matColSize) {
    int m = matSize, n = matColSize[0];
    int rows[m];
    int cols[n];
    memset(rows, 0, sizeof(rows));
    memset(cols, 0, sizeof(cols));

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            rows[i] += mat[i][j];
            cols[j] += mat[i][j];
        }
    }

    int ans = 0;
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                ans++;
            }
        }
    }

    return ans;
}
