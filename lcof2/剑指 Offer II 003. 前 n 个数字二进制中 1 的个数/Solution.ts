function countBits(n: number): number[] {
    const f: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        f[i] = f[i & (i - 1)] + 1;
    }
    return f;
}
