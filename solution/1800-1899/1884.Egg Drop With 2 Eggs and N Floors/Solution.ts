function twoEggDrop(n: number): number {
    const f: number[] = Array(n + 1).fill(Infinity);
    f[0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 1; j <= i; ++j) {
            f[i] = Math.min(f[i], 1 + Math.max(j - 1, f[i - j]));
        }
    }
    return f[n];
}
