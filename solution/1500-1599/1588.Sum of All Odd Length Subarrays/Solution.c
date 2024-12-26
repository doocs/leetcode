int sumOddLengthSubarrays(int* arr, int arrSize) {
    int n = arrSize;
    int f[n];
    int g[n];
    int ans = f[0] = arr[0];
    g[0] = 0;
    for (int i = 1; i < n; ++i) {
        f[i] = g[i - 1] + arr[i] * (i / 2 + 1);
        g[i] = f[i - 1] + arr[i] * ((i + 1) / 2);
        ans += f[i];
    }
    return ans;
}
