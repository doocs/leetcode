#define min(a, b) (((a) < (b)) ? (a) : (b))

int minimumRecolors(char* blocks, int k) {
    int n = strlen(blocks);
    int count = 0;
    for (int i = 0; i < k; i++) {
        count += blocks[i] == 'B';
    }
    int ans = k - count;
    for (int i = k; i < n; i++) {
        count -= blocks[i - k] == 'B';
        count += blocks[i] == 'B';
        ans = min(ans, k - count);
    }
    return ans;
}
