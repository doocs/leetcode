bool makeStringsEqual(char* s, char* target) {
    int count = 0;
    for (int i = 0; s[i]; i++) {
        if (s[i] == '1') {
            count++;
            break;
        }
    }
    for (int i = 0; target[i]; i++) {
        if (target[i] == '1') {
            count++;
            break;
        }
    }
    return !(count & 1);
}
