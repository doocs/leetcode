function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const stk = [];
    const ans = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = prices[i];
        while (stk.length && prices[stk[stk.length - 1]] >= prices[i]) {
            ans[stk.pop()] -= prices[i];
        }
        stk.push(i);
    }
    return ans;
}
