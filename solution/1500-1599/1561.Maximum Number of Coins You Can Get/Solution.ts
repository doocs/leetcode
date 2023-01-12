function maxCoins(piles: number[]): number {
    piles.sort((a, b) => a - b);
    const n = piles.length;
    let ans = 0;
    for (let i = 1; i <= Math.floor(n / 3); i++) {
        ans += piles[n - 2 * i];
    }
    return ans;
}
