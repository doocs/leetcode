int minMaxDifference(int num) {
    char s[12];
    sprintf(s, "%d", num);

    int mi;
    {
        char tmp[12];
        char t = s[0];
        for (int i = 0; s[i]; i++) {
            tmp[i] = (s[i] == t) ? '0' : s[i];
        }
        tmp[strlen(s)] = '\0';
        mi = atoi(tmp);
    }

    for (int i = 0; s[i]; i++) {
        char c = s[i];
        if (c != '9') {
            char tmp[12];
            for (int j = 0; s[j]; j++) {
                tmp[j] = (s[j] == c) ? '9' : s[j];
            }
            tmp[strlen(s)] = '\0';
            return atoi(tmp) - mi;
        }
    }

    return num - mi;
}
