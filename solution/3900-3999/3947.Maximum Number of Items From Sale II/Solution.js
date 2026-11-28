var maximumSaleItems = function (items, budget) {
    const n = items.length;
    const freq = new Int32Array(n + 1);
    let minPrice = Infinity;

    for (let i = 0; i < n; i++) {
        freq[items[i][0]]++;
        if (items[i][1] < minPrice) minPrice = items[i][1];
    }

    const divCount = new Int32Array(n + 1);
    for (let f = 1; f <= n; f++) {
        if (freq[f] === 0) continue;
        for (let m = f; m <= n; m += f) {
            divCount[f] += freq[m];
        }
    }

    const sortedItems = items.map(([factor, price]) => [price, divCount[factor] - 1]);
    sortedItems.sort((a, b) => a[0] - b[0]);

    let rem = budget;
    let ans = 0;
    const limit = 2 * minPrice;

    for (let i = 0; i < n; i++) {
        const [price, gain] = sortedItems[i];
        if (price >= limit) break;
        if (gain <= 0) continue;

        const canBuy = Math.min(gain, Math.floor(rem / price));
        ans += canBuy * 2;
        rem -= canBuy * price;

        if (canBuy < gain) break;
    }

    ans += Math.floor(rem / minPrice);
    return ans;
};
