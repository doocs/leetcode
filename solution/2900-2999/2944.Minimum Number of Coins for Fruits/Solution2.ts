function minimumCoins(prices: number[]): number {
    for (let i = (prices.length - 1) >> 1; i; --i) {
        prices[i - 1] += Math.min(...prices.slice(i, i * 2 + 1));
    }
    return prices[0];
}
