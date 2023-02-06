int cmp(const void* a, const void* b) {
    return *(int*) a - *(int*) b;
}

int maxCoins(int* piles, int pilesSize) {
    qsort(piles, pilesSize, sizeof(int), cmp);
    int ans = 0;
    for (int i = 1; i <= pilesSize / 3; i++) {
        ans += piles[pilesSize - 2 * i];
    };
    return ans;
}
