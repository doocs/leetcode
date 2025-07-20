char* convert(char* s, int numRows) {
    if (numRows == 1) {
        return strdup(s);
    }

    int len = strlen(s);
    char** g = (char**) malloc(numRows * sizeof(char*));
    int* idx = (int*) malloc(numRows * sizeof(int));
    for (int i = 0; i < numRows; ++i) {
        g[i] = (char*) malloc((len + 1) * sizeof(char));
        idx[i] = 0;
    }

    int i = 0, k = -1;
    for (int p = 0; p < len; ++p) {
        g[i][idx[i]++] = s[p];
        if (i == 0 || i == numRows - 1) {
            k = -k;
        }
        i += k;
    }

    char* ans = (char*) malloc((len + 1) * sizeof(char));
    int pos = 0;
    for (int r = 0; r < numRows; ++r) {
        for (int j = 0; j < idx[r]; ++j) {
            ans[pos++] = g[r][j];
        }
        free(g[r]);
    }
    ans[pos] = '\0';

    free(g);
    free(idx);
    return ans;
}
