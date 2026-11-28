function maximumSaleItems(items: number[][], budget: number): number {
    const n = items.length;
    const freq = new Array(n + 1).fill(0);
    const cnt = new Array(n + 1).fill(0);

    let minPrice = Infinity;

    for (const [f, p] of items) {
        freq[f]++;
        minPrice = Math.min(minPrice, p);
    }

    for (let f = 1; f <= n; f++) {
        for (let j = f; j <= n; j += f) {
            cnt[f] += freq[j];
        }
        cnt[f]--;
    }

    const deals: number[][] = [];

    for (const [f, p] of items) {
        if (cnt[f] > 0 && p <= 2 * minPrice) {
            deals.push([p, cnt[f]]);
        }
    }

    deals.sort((a, b) => a[0] - b[0]);

    let ans = 0;

    for (const [price, count] of deals) {
        const take = Math.min(count, Math.floor(budget / price));
        ans += take * 2;
        budget -= take * price;
    }

    return ans + Math.floor(budget / minPrice);
}
