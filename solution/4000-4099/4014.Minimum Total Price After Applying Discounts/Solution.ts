function minPrice(prices: number[], discounts: number[]): number {
    prices.sort((a, b) => a - b);
    discounts.sort((a, b) => a - b);

    let i = prices.length - 1;
    let j = discounts.length - 1;

    let ans = 0;

    while (i >= 0 && j >= 0) {
        ans += (prices[i] * (100 - discounts[j])) / 100;
        i--;
        j--;
    }

    while (i >= 0) {
        ans += prices[i];
        i--;
    }

    return ans;
}
