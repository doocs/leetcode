/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** matrixReshape(int** mat, int matSize, int* matColSize, int r, int c, int* returnSize, int** returnColumnSizes) {
    if (matSize * matColSize[0] != r * c) {
        *returnSize = matSize;
        *returnColumnSizes = matColSize;
        return mat;
    }
    *returnSize = r;
    *returnColumnSizes = malloc(sizeof(int) * r);
    int** ans = malloc(sizeof(int*) * r);
    for (int i = 0; i < r; i++) {
        (*returnColumnSizes)[i] = c;
        ans[i] = malloc(sizeof(int) * c);
    }
    for (int i = 0; i < r * c; i++) {
        ans[i / c][i % c] = mat[i / matColSize[0]][i % matColSize[0]];
    }
    return ans;
}
