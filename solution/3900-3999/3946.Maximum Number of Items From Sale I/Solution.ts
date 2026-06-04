function maximumSaleItems(items: number[][], budget: number): number {
    const f: number[] = new Array(budget + 1).fill(0);
    let mn: number = Infinity;

    for (const [factor, price] of items) {
        mn = Math.min(mn, price);

        let cnt = 0;
        for (const [factor_j, _] of items) {
            if (factor_j % factor === 0) {
                cnt++;
            }
        }

        for (let j = budget; j >= price; j--) {
            f[j] = Math.max(f[j], f[j - price] + cnt);
        }
    }

    let ans = 0;
    for (let i = 0; i <= budget; i++) {
        ans = Math.max(ans, f[i] + Math.floor((budget - i) / mn));
    }

    return ans;
}
