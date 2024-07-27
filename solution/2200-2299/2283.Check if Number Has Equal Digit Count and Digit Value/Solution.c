bool digitCount(char* num) {
    int cnt[10] = {0};
    for (int i = 0; num[i] != '\0'; ++i) {
        ++cnt[num[i] - '0'];
    }
    for (int i = 0; num[i] != '\0'; ++i) {
        if (cnt[i] != num[i] - '0') {
            return false;
        }
    }
    return true;
}