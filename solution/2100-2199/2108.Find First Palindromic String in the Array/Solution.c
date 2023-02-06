char* firstPalindrome(char** words, int wordsSize) {
    for (int i = 0; i < wordsSize; i++) {
        int left = 0;
        int right = strlen(words[i]) - 1;
        while (left < right) {
            if (words[i][left] != words[i][right]) {
                break;
            }
            left++;
            right--;
        }
        if (left >= right) {
            return words[i];
        }
    }
    return "";
}
