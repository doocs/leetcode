void setZeroes(int** matrix, int matrixSize, int* matrixColSize) {
    int m = matrixSize;
    int n = matrixColSize[0];
    int l0 = 0;
    int r0 = 0;
    for (int i = 0; i < m; i++) {
        if (matrix[i][0] == 0) {
            l0 = 1;
            break;
        }
    }
    for (int j = 0; j < n; j++) {
        if (matrix[0][j] == 0) {
            r0 = 1;
            break;
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (matrix[i][j] == 0) {
                matrix[i][0] = 0;
                matrix[0][j] = 0;
            }
        }
    }
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                matrix[i][j] = 0;
            }
        }
    }
    if (l0) {
        for (int i = 0; i < m; i++) {
            matrix[i][0] = 0;
        }
    }
    if (r0) {
        for (int j = 0; j < n; j++) {
            matrix[0][j] = 0;
        }
    }
}
