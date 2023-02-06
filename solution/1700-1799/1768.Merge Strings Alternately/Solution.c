char* mergeAlternately(char* word1, char* word2) {
    int m = strlen(word1);
    int n = strlen(word2);
    char* ans = malloc(sizeof(char) * (n + m + 1));
    int i = 0;
    int j = 0;
    while (i + j != m + n) {
        if (i < m) {
            ans[i + j] = word1[i];
            i++;
        }
        if (j < n) {
            ans[i + j] = word2[j];
            j++;
        }
    }
    ans[n + m] = '\0';
    return ans;
}
