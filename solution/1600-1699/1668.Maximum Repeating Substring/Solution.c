#define max(a, b) (((a) > (b)) ? (a) : (b))

int findWord(int i, char* sequence, char* word) {
    int n = strlen(word);
    for (int j = 0; j < n; j++) {
        if (sequence[j + i] != word[j]) {
            return 0;
        }
    }
    return 1 + findWord(i + n, sequence, word);
}

int maxRepeating(char* sequence, char* word) {
    int n = strlen(sequence);
    int m = strlen(word);
    int ans = 0;
    for (int i = 0; i <= n - m; i++) {
        ans = max(ans, findWord(i, sequence, word));
    }
    return ans;
}
