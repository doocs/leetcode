int minCount(int* coins, int coinsSize) {
    int res = 0;
    for (int i = 0; i < coinsSize; i++) {
        int coin = coins[i];
        if (coin % 2 == 1) {
            res++;
        }
        res += coin / 2;
    }
    return res;
}
