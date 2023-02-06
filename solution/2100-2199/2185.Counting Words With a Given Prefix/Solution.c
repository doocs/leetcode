int prefixCount(char** words, int wordsSize, char* pref) {
    int ans = 0;
    int n = strlen(pref);
    for (int i = 0; i < wordsSize; i++) {
        if (strncmp(words[i], pref, n) == 0) {
            ans++;
        }
    }
    return ans;
}
