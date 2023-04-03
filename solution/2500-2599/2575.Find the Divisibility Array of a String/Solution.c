/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* divisibilityArray(char* word, int m, int* returnSize) {
    int n = strlen(word);
    int* ans = malloc(sizeof(int) * n);
    long long x = 0;
    for (int i = 0; i < n; i++) {
        x = (x * 10 + word[i] - '0') % m;
        ans[i] = x == 0 ? 1 : 0;
    }
    *returnSize = n;
    return ans;
}
