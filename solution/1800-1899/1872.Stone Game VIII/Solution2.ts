function stoneGameVIII(stones: number[]): number {
    const n = stones.length;
    for (let i = 1; i < n; ++i) {
        stones[i] += stones[i - 1];
    }
    let f = stones[n - 1];
    for (let i = n - 2; i; --i) {
        f = Math.max(f, stones[i] - f);
    }
    return f;
}
