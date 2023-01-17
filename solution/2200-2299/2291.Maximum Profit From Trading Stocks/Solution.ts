function maximumProfit(
    present: number[],
    future: number[],
    budget: number,
): number {
    const f = new Array(budget + 1).fill(0);
    for (let i = 0; i < present.length; ++i) {
        const [a, b] = [present[i], future[i]];
        for (let j = budget; j >= a; --j) {
            f[j] = Math.max(f[j], f[j - a] + b - a);
        }
    }
    return f[budget];
}
