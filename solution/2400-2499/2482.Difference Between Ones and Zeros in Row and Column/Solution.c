/**
 * Return an array of arrays of size *returnSize.
 * The sizes of the arrays are returned as *returnColumnSizes array.
 * Note: Both returned array and *columnSizes array must be malloced, assume caller calls free().
 */
int** onesMinusZeros(int** grid, int gridSize, int* gridColSize, int* returnSize, int** returnColumnSizes) {
    int* rows = malloc(sizeof(int) * gridSize);
    int* cols = malloc(sizeof(int) * gridColSize[0]);
    memset(rows, 0, sizeof(int) * gridSize);
    memset(cols, 0, sizeof(int) * gridColSize[0]);
    for (int i = 0; i < gridSize; i++) {
        for (int j = 0; j < gridColSize[0]; j++) {
            if (grid[i][j]) {
                rows[i]++;
                cols[j]++;
            }
        }
    }
    int** ans = malloc(sizeof(int*) * gridSize);
    for (int i = 0; i < gridSize; i++) {
        ans[i] = malloc(sizeof(int) * gridColSize[0]);
        for (int j = 0; j < gridColSize[0]; j++) {
            ans[i][j] = rows[i] + cols[j] - (gridSize - rows[i]) - (gridColSize[0] - cols[j]);
        }
    }
    *returnSize = gridSize;
    *returnColumnSizes = gridColSize;
    return ans;
}
