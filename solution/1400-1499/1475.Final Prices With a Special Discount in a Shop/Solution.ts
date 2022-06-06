function finalPrices(prices: number[]): number[] {
    const n = prices.length;
    const res = new Array(n);
    const stack = [];
    for (let i = n - 1; i >= 0; i--) {
        const price = prices[i];
        while (stack.length !== 0 && stack[stack.length - 1] > price) {
            stack.pop();
        }
        res[i] = price - (stack[stack.length - 1] ?? 0);
        stack.push(price);
    }
    return res;
}
