int sumOddLengthSubarrays(int* arr, int arrSize) {
    int ans = 0;
    for (int i = 0; i < arrSize; ++i) {
        int s = 0;
        for (int j = i; j < arrSize; ++j) {
            s += arr[j];
            if ((j - i + 1) % 2 == 1) {
                ans += s;
            }
        }
    }
    return ans;
}