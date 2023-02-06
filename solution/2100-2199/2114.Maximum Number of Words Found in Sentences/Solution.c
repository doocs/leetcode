#define max(a, b) (((a) > (b)) ? (a) : (b))

int mostWordsFound(char** sentences, int sentencesSize) {
    int ans = 0;
    for (int i = 0; i < sentencesSize; i++) {
        char* s = sentences[i];
        int count = 1;
        for (int j = 0; s[j]; j++) {
            if (s[j] == ' ') {
                count++;
            }
        }
        ans = max(ans, count);
    }
    return ans;
}
