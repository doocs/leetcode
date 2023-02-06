/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* countPoints(int** points, int pointsSize, int* pointsColSize, int** queries, int queriesSize, int* queriesColSize,
    int* returnSize) {
    int* ans = malloc(sizeof(int) * queriesSize);
    for (int i = 0; i < queriesSize; i++) {
        int cx = queries[i][0];
        int cy = queries[i][1];
        int r = queries[i][2];
        int count = 0;
        for (int j = 0; j < pointsSize; j++) {
            if (sqrt(pow(points[j][0] - cx, 2) + pow(points[j][1] - cy, 2)) <= r) {
                count++;
            }
        }
        ans[i] = count;
    }
    *returnSize = queriesSize;
    return ans;
}
