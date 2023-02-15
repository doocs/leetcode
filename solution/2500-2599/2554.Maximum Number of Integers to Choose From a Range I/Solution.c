int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int maxCount(int* banned, int bannedSize, int n, int maxSum) {
    qsort(banned, bannedSize, sizeof(int), cmp);
    int sum = 0;
    int ans = 0;
    for (int i = 1, j = 0; i <= n; i++) {
        if (sum + i > maxSum) {
            break;
        }
        if (j < bannedSize && i == banned[j]) {
            while (j < bannedSize && i == banned[j]) {
                j++;
            }
        } else {
            sum += i;
            ans++;
        }
    }
    return ans;
}