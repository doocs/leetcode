int numSpecial(int** mat, int matSize, int* matColSize) {
    int m = matSize;
    int n = *matColSize;
    int* rows = (int*) malloc(sizeof(int) * m);
    int* cols = (int*) malloc(sizeof(int) * n);
    memset(rows, 0, sizeof(int) * m);
    memset(cols, 0, sizeof(int) * n);
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    int res = 0;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (mat[i][j] == 1 && rows[i] == 1 && cols[j] == 1) {
                res++;
            }
        }
    }
    free(rows);
    free(cols);
    return res;
}
