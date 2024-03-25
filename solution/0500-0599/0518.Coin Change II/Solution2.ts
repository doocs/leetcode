function change(amount: number, coins: number[]): number {
    const n = amount;
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (const x of coins) {
        for (let j = x; j <= n; ++j) {
            f[j] += f[j - x];
        }
    }
    return f[n];
}
