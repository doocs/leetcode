/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int countOnes(int n) {
    int res = 0;
    while (n) {
        n &= n - 1;
        res++;
    }
    return res;
}

int cmp(const void* _a, const void* _b) {
    int a = *(int*) _a;
    int b = *(int*) _b;
    int res = countOnes(a) - countOnes(b);
    if (res == 0) {
        return a - b;
    }
    return res;
}

int* sortByBits(int* arr, int arrSize, int* returnSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    *returnSize = arrSize;
    return arr;
}
