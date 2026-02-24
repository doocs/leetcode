static int bitCount(int x) {
    int cnt = 0;
    while (x) {
        x &= (x - 1);
        ++cnt;
    }
    return cnt;
}

static int cmp(const void* a, const void* b) {
    int x = *(const int*) a;
    int y = *(const int*) b;

    int cx = bitCount(x);
    int cy = bitCount(y);

    if (cx != cy) {
        return cx - cy;
    }
    return x - y;
}

/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* sortByBits(int* arr, int arrSize, int* returnSize) {
    *returnSize = arrSize;

    int* res = (int*) malloc(sizeof(int) * arrSize);
    if (!res) {
        return NULL;
    }

    for (int i = 0; i < arrSize; ++i) {
        res[i] = arr[i];
    }

    qsort(res, arrSize, sizeof(int), cmp);

    return res;
}
