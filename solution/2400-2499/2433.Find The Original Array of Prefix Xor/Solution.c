/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* findArray(int* pref, int prefSize, int* returnSize) {
    int* res = (int*) malloc(sizeof(int) * prefSize);
    res[0] = pref[0];
    for (int i = 1; i < prefSize; i++) {
        res[i] = pref[i - 1] ^ pref[i];
    }
    *returnSize = prefSize;
    return res;
}
