function maxProfit(prices: number[], fee: number): number {
    const n = prices.length;
    let [f0, f1] = [0, -prices[0]];
    for (const x of prices.slice(1)) {
        [f0, f1] = [Math.max(f0, f1 + x - fee), Math.max(f1, f0 - x)];
    }
    return f0;
}
