int f(const char* s) {
    int ans = 0;
    while (*s) {
        ans = ans * 10 + (*s - 'a');
        s++;
    }
    return ans;
}

bool isSumEqual(char* firstWord, char* secondWord, char* targetWord) {
    return f(firstWord) + f(secondWord) == f(targetWord);
}
