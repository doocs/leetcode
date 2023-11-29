function numTrees(n: number): number {
    const f: number[] = Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < i; ++j) {
            f[i] += f[j] * f[i - j - 1];
        }
    }
    return f[n];
}
