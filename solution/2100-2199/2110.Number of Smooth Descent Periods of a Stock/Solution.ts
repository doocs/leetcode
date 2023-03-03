function getDescentPeriods(prices: number[]): number {
    let ans = 0;
    const n = prices.length;
    for (let i = 0, j = 0; i < n; i = j) {
        j = i + 1;
        while (j < n && prices[j - 1] - prices[j] === 1) {
            ++j;
        }
        const cnt = j - i;
        ans += Math.floor(((1 + cnt) * cnt) / 2);
    }
    return ans;
}
