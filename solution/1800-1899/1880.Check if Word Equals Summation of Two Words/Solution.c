int calc(char* s) {
    int res = 0;
    for (int i = 0; s[i]; i++) {
        res = res * 10 + s[i] - 'a';
    }
    return res;
}

bool isSumEqual(char* firstWord, char* secondWord, char* targetWord) {
    return calc(firstWord) + calc(secondWord) == calc(targetWord);
}
