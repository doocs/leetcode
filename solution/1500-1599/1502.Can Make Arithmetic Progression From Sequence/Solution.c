int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

bool canMakeArithmeticProgression(int* arr, int arrSize) {
    qsort(arr, arrSize, sizeof(int), cmp);
    for (int i = 2; i < arrSize; i++) {
        if (arr[i - 2] - arr[i - 1] != arr[i - 1] - arr[i]) {
            return 0;
        }
    }
    return 1;
}
