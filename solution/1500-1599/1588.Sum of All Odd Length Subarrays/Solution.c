int sumOddLengthSubarrays(int *arr, int arrSize) {
    int ans = 0;
    for (int i = 1; i <= arrSize; i += 2) {
        int sum = 0;
        for (int j = 0; j < i; j++) {
            sum += arr[j];
        }
        ans += sum;
        for (int j = i; j < arrSize; j++) {
            sum += arr[j] - arr[j - i];
            ans += sum;
        }
    }
    return ans;
}
