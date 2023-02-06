bool digitCount(char* num) {
    int count[10] = {0};
    for (int i = 0; num[i]; i++) {
        count[i] = num[i] - '0';
    }
    for (int i = 0; num[i]; i++) {
        count[num[i] - '0']--;
    }
    for (int i = 0; i < 10; i++) {
        if (count[i] != 0) {
            return false;
        }
    }
    return true;
}
