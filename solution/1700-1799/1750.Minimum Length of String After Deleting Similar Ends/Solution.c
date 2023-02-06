int minimumLength(char* s) {
    int n = strlen(s);
    int start = 0;
    int end = n - 1;
    while (start < end && s[start] == s[end]) {
        while (start + 1 < end && s[start] == s[start + 1]) {
            start++;
        }
        while (start < end - 1 && s[end] == s[end - 1]) {
            end--;
        }
        start++;
        end--;
    }
    if (start > end) {
        return 0;
    }
    return end - start + 1;
}
