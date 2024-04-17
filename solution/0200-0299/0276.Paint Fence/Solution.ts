function numWays(n: number, k: number): number {
    const f: number[] = Array(n).fill(0);
    const g: number[] = Array(n).fill(0);
    f[0] = k;
    for (let i = 1; i < n; ++i) {
        f[i] = (f[i - 1] + g[i - 1]) * (k - 1);
        g[i] = f[i - 1];
    }
    return f[n - 1] + g[n - 1];
}
