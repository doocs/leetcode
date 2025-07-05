char* d[] = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

char** letterCombinations(char* digits, int* returnSize) {
    if (!*digits) {
        *returnSize = 0;
        return NULL;
    }

    int size = 1;
    char** ans = (char**) malloc(sizeof(char*));
    ans[0] = strdup("");

    for (int x = 0; digits[x]; ++x) {
        char* s = d[digits[x] - '2'];
        int len = strlen(s);
        char** t = (char**) malloc(sizeof(char*) * size * len);
        int tSize = 0;

        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < len; ++j) {
                int oldLen = strlen(ans[i]);
                char* tmp = (char*) malloc(oldLen + 2);
                strcpy(tmp, ans[i]);
                tmp[oldLen] = s[j];
                tmp[oldLen + 1] = '\0';
                t[tSize++] = tmp;
            }
            free(ans[i]);
        }
        free(ans);
        ans = t;
        size = tSize;
    }

    *returnSize = size;
    return ans;
}
