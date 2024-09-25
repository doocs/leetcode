int compare(const void* a, const void* b) {
    return (*(int*) a - *(int*) b);
}

int maxCoins(int* piles, int pilesSize) {
    qsort(piles, pilesSize, sizeof(int), compare);
    int ans = 0;
    for (int i = pilesSize / 3; i < pilesSize; i += 2) {
        ans += piles[i];
    }
    return ans;
}
