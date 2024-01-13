int countConsistentStrings(char* allowed, char** words, int wordsSize) {
    int n = strlen(allowed);
    int make[26] = {0};
    for (int i = 0; i < n; i++) {
        make[allowed[i] - 'a'] = 1;
    }
    int ans = wordsSize;
    for (int i = 0; i < wordsSize; i++) {
        char* word = words[i];
        for (int j = 0; j < strlen(word); j++) {
            if (!make[word[j] - 'a']) {
                ans--;
                break;
            }
        }
    }
    return ans;
}