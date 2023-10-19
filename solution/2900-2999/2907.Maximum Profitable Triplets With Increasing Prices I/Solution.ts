function maxProfit(prices: number[], profits: number[]): number {
    const n = prices.length;
    let ans = -1;
    for (let j = 0; j < n; ++j) {
        let [left, right] = [0, 0];
        for (let i = 0; i < j; ++i) {
            if (prices[i] < prices[j]) {
                left = Math.max(left, profits[i]);
            }
        }
        for (let k = j + 1; k < n; ++k) {
            if (prices[j] < prices[k]) {
                right = Math.max(right, profits[k]);
            }
        }
        if (left > 0 && right > 0) {
            ans = Math.max(ans, left + profits[j] + right);
        }
    }
    return ans;
}
