/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
char** buildArray(int* target, int targetSize, int n, int* returnSize) {
    char** res = (char**) malloc(sizeof(char*) * n * 2);
    int cur = 1;
    int i = 0;
    for (int j = 0; j < targetSize; j++) {
        while (++cur < target[j]) {
            res[i] = (char*) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Push");
            res[i] = (char*) malloc(sizeof(char) * 8);
            strcpy(res[i++], "Pop");
        }
        res[i] = (char*) malloc(sizeof(char) * 8);
        strcpy(res[i++], "Push");
    }
    *returnSize = i;
    return res;
}
