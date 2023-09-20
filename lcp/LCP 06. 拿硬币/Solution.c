int minCount(int* coins, int coinsSize) {
    int ans = 0;
    for (int i = 0; i < coinsSize; ++i) {
        ans += (coins[i] + 1) >> 1;
    }
    return ans;
}
