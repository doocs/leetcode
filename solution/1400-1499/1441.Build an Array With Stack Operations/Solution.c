/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** buildArray(int* target, int targetSize, int n, int* returnSize) {
    char** ans = (char**) malloc(sizeof(char*) * (2 * n));
    *returnSize = 0;
    int cur = 1;
    for (int i = 0; i < targetSize; i++) {
        while (cur < target[i]) {
            ans[(*returnSize)++] = "Push";
            ans[(*returnSize)++] = "Pop";
            cur++;
        }
        ans[(*returnSize)++] = "Push";
        cur++;
    }
    return ans;
}
