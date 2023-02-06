bool areNumbersAscending(char* s) {
    int pre = -1;
    int cur = 0;
    for (int i = 0; s[i]; i++) {
        if (isdigit(s[i])) {
            cur = cur * 10 + s[i] - '0';
        } else {
            if (cur != 0) {
                if (cur <= pre) {
                    return 0;
                }
                pre = cur;
                cur = 0;
            }
        }
    }
    if (cur != 0 && cur <= pre) {
        return 0;
    }
    return 1;
}
