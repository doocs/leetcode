bool arrayStringsAreEqual(char** word1, int word1Size, char** word2, int word2Size) {
    int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
    while (i1 < word1Size && i2 < word2Size) {
        if (word1[i1][j1] != word2[i2][j2]) {
            return 0;
        }
        j1++;
        if (word1[i1][j1] == '\0') {
            i1++;
            j1 = 0;
        }
        j2++;
        if (word2[i2][j2] == '\0') {
            i2++;
            j2 = 0;
        }
    }
    return i1 == word1Size && i2 == word2Size;
}
