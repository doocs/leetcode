#define max(a, b) (((a) > (b)) ? (a) : (b))

int maxChunksToSorted(int* arr, int arrSize) {
    int res = 0;
    int mx = -1;
    for (int i = 0; i < arrSize; i++) {
        mx = max(mx, arr[i]);
        if (mx == i) {
            res++;
        }
    }
    return res;
}
