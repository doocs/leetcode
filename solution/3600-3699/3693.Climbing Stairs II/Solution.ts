function climbStairs(n: number, costs: number[]): number {
    const inf = Number.MAX_SAFE_INTEGER / 2;
    const f = Array(n + 1).fill(inf);
    f[0] = 0;

    for (let i = 1; i <= n; ++i) {
        const x = costs[i - 1];
        for (let j = Math.max(0, i - 3); j < i; ++j) {
            f[i] = Math.min(f[i], f[j] + x + (i - j) * (i - j));
        }
    }
    return f[n];
}
