function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    const f: number[] = Array(n).fill(0);
    f[n - 1] = stones[n - 1];
    for (let i = n - 2; i > 0; --i) {
        f[i] = Math.max(f[i + 1], stones[i] - f[i + 1]);
    }
    return f[1];
}
