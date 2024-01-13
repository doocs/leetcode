/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* minOperations(char* boxes, int* returnSize) {
    int n = strlen(boxes);
    int* left = malloc(sizeof(int) * n);
    int* right = malloc(sizeof(int) * n);
    memset(left, 0, sizeof(int) * n);
    memset(right, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        left[i] = left[i - 1] + count;
    }
    for (int i = n - 2, count = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        right[i] = right[i + 1] + count;
    }
    int* ans = malloc(sizeof(int) * n);
    for (int i = 0; i < n; i++) {
        ans[i] = left[i] + right[i];
    }
    free(left);
    free(right);
    *returnSize = n;
    return ans;
}