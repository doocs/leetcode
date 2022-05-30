function minimumLines(stockPrices: number[][]): number {
    const n = stockPrices.length;
    stockPrices.sort((a, b) => a[0] - b[0]);
    let ans = 0;
    let pre = [BigInt(0), BigInt(0)];
    for (let i = 1; i < n; i++) {
        const [x1, y1] = stockPrices[i - 1];
        const [x2, y2] = stockPrices[i];
        const dx = BigInt(x2 - x1),
            dy = BigInt(y2 - y1);
        if (i == 1 || dx * pre[1] !== dy * pre[0]) ans++;
        pre = [dx, dy];
    }
    return ans;
}
