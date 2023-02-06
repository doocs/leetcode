/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* minOperations(char* boxes, int* returnSize) {
    int n = strlen(boxes);
    int* ans = malloc(sizeof(int) * n);
    memset(ans, 0, sizeof(int) * n);
    for (int i = 1, count = 0; i < n; i++) {
        if (boxes[i - 1] == '1') {
            count++;
        }
        ans[i] = ans[i - 1] + count;
    }
    for (int i = n - 2, count = 0, sum = 0; i >= 0; i--) {
        if (boxes[i + 1] == '1') {
            count++;
        }
        sum += count;
        ans[i] += sum;
    }
    *returnSize = n;
    return ans;
}
