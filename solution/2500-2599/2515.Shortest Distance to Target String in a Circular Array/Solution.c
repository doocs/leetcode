int closetTarget(char** words, int wordsSize, char* target, int startIndex) {
    for (int i = 0; i <= wordsSize >> 1; i++) {
        if (strcmp(words[(startIndex - i + wordsSize) % wordsSize], target) == 0 || strcmp(words[(startIndex + i) % wordsSize], target) == 0) {
            return i;
        }
    }
    return -1;
}
