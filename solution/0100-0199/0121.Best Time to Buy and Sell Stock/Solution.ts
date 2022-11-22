function maxProfit(prices: number[]): number {
    let ans = 0;
    let mi = prices[0];
    for (const v of prices) {
        ans = Math.max(ans, v - mi);
        mi = Math.min(mi, v);
    }
    return ans;
}
