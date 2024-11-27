int diagonalSum(int** mat, int matSize, int* matColSize) {
    int ans = 0;
    for (int i = 0; i < matSize; ++i) {
        ans += mat[i][i];
        int j = matSize - i - 1;
        if (j != i) {
            ans += mat[i][j];
        }
    }
    return ans;
}
