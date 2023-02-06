char* largestMerge(char* word1, char* word2) {
    int m = strlen(word1);
    int n = strlen(word2);
    int i = 0;
    int j = 0;
    char* ans = malloc((m + n + 1) * sizeof(char));
    while (i < m && j < n) {
        int k = 0;
        while (word1[i + k] && word2[j + k] && word1[i + k] == word2[j + k]) {
            k++;
        }
        if (word1[i + k] > word2[j + k]) {
            ans[i + j] = word1[i];
            i++;
        } else {
            ans[i + j] = word2[j];
            j++;
        };
    }
    while (word1[i]) {
        ans[i + j] = word1[i];
        i++;
    }
    while (word2[j]) {
        ans[i + j] = word2[j];
        j++;
    }
    ans[m + n] = '\0';
    return ans;
}
