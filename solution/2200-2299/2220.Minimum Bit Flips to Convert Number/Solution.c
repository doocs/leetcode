int minBitFlips(int start, int goal) {
    int x = start ^ goal;
    int ans = 0;
    while (x) {
        ans += (x & 1);
        x >>= 1;
    }
    return ans;
}
