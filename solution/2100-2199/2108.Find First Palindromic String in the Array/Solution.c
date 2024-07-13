char* firstPalindrome(char** words, int wordsSize) {
    for (int i = 0; i < wordsSize; ++i) {
        char* w = words[i];
        int len = strlen(w);
        bool ok = true;
        for (int j = 0, k = len - 1; j < k && ok; ++j, --k) {
            if (w[j] != w[k]) {
                ok = false;
            }
        }
        if (ok) {
            return w;
        }
    }
    return "";
}