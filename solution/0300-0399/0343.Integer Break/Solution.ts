function integerBreak(n: number): number {
    const f = Array(n + 1).fill(1);
    for (let i = 3; i <= n; i++) {
        for (let j = 1; j < i; j++) {
            f[i] = Math.max(f[i], j * (i - j), j * f[i - j]);
        }
    }
    return f[n];
}
