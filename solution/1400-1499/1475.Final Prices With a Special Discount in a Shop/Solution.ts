function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = prices[i];
        for (let j = i + 1; j < n; ++j) {
            if (prices[j] <= prices[i]) {
                ans[i] -= prices[j];
                break;
            }
        }
    }
    return ans;
}
