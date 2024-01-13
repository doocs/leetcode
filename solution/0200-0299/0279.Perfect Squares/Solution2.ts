function numSquares(n: number): number {
    const m = Math.floor(Math.sqrt(n));
    const f: number[] = Array(n + 1).fill(1 << 30);
    f[0] = 0;
    for (let i = 1; i <= m; ++i) {
        for (let j = i * i; j <= n; ++j) {
            f[j] = Math.min(f[j], f[j - i * i] + 1);
        }
    }
    return f[n];
}
