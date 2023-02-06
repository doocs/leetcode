int diagonalSum(int** mat, int matSize, int* matColSize) {
    int ans = 0;
    for (int i = 0; i < matSize; i++) {
        ans += mat[i][i] + mat[i][matSize - 1 - i];
    }
    if (matSize & 1) {
        ans -= mat[matSize >> 1][matSize >> 1];
    }
    return ans;
}
