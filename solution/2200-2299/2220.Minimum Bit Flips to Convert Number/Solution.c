int minBitFlips(int start, int goal) {
    int tmp = start ^ goal;
    int ans = 0;
    while (tmp) {
        ans += tmp & 1;
        tmp >>= 1;
    }
    return ans;
}
