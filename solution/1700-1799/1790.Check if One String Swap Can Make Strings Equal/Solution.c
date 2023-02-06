bool areAlmostEqual(char* s1, char* s2) {
    int n = strlen(s1);
    int i1 = -1;
    int i2 = -1;
    for (int i = 0; i < n; i++) {
        if (s1[i] != s2[i]) {
            if (i1 == -1) {
                i1 = i;
            } else if (i2 == -1) {
                i2 = i;
            } else {
                return 0;
            }
        }
    }
    if (i1 == -1 && i2 == -1) {
        return 1;
    }
    if (i1 == -1 || i2 == -1) {
        return 0;
    }
    return s1[i1] == s2[i2] && s1[i2] == s2[i1];
}
