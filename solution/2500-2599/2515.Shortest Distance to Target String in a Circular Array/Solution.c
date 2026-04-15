#define min(a, b) ((a) < (b) ? (a) : (b))

int closestTarget(char** words, int wordsSize, char* target, int startIndex) {
    int n = wordsSize;
    int ans = n;

    for (int i = 0; i < n; i++) {
        if (strcmp(words[i], target) == 0) {
            int t = abs(i - startIndex);
            int dist = min(t, n - t);
            ans = min(ans, dist);
        }
    }

    return ans == n ? -1 : ans;
}
