function maxProfit(prices: number[]): number {
    let res = 0;
    let min = Infinity;
    for (const price of prices) {
        res = Math.max(res, price - min);
        min = Math.min(min, price);
    }
    return res;
}
