int zigZagArrays(int n, int low, int high) {
    int range = high - low;
    int mod = 1000000007, *dp, *ptr, *end, i = 1, goingUp = 1;
    long long ans = 0;
    if (range < 1 || !(dp = malloc(range * sizeof(int)))) return 0;
    ptr = dp;
    end = dp + range;
    while (ptr < end) *ptr++ = 1;
    ptr = dp + 1;
    while (ptr < end) *ptr += ptr[-1], ptr++;
    for (; i < n - 1; i++) {
        if (goingUp) {
            ptr = dp + range - 2;
            while (ptr >= dp) *ptr += ptr[1], *ptr -= *ptr >= mod ? mod : 0, ptr--;
        } else {
            ptr = dp + 1;
            while (ptr < end) *ptr += ptr[-1], *ptr -= *ptr >= mod ? mod : 0, ptr++;
        }
        goingUp ^= 1;
    }
    ptr = dp;
    while (ptr < end) ans += *ptr++;
    free(dp);
    return (int) (ans * 2 % mod);
}
