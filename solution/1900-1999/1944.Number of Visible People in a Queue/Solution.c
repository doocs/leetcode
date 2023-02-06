/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* canSeePersonsCount(int* heights, int heightsSize, int* returnSize) {
    int* ans = malloc(sizeof(int) * heightsSize);
    memset(ans, 0, sizeof(int) * heightsSize);
    int stack[heightsSize];
    int i = 0;
    for (int j = heightsSize - 1; j >= 0; j--) {
        while (i) {
            ans[j]++;
            if (heights[j] <= heights[stack[i - 1]]) {
                break;
            }
            i--;
        }
        stack[i++] = j;
    }
    *returnSize = heightsSize;
    return ans;
}
