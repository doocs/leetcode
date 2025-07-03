char* map[] = {"", "", "abc", "def", "ghi",
    "jkl", "mno", "pqrs", "tuv", "wxyz"};

void backtrack(char* d, int i, char* cur, char** res, int* sz) {
    if (!d[i]) {
        res[(*sz)++] = strdup(cur);
        return;
    }
    for (char* p = map[d[i] - '0']; *p; p++) {
        cur[i] = *p;
        backtrack(d, i + 1, cur, res, sz);
    }
}

char** letterCombinations(char* d, int* sz) {
    *sz = 0;
    if (!*d)
        return NULL;
    int max = 1, len = strlen(d);
    for (int i = 0; i < len; i++)
        max *= (d[i] == '7' || d[i] == '9') ? 4 : 3;
    char **res = malloc(max * sizeof(char*)), *cur = malloc(len + 1);
    cur[len] = '\0';
    backtrack(d, 0, cur, res, sz);
    free(cur);
    return res;
}
