function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const f: number[] = Array(n + 2).fill(0);
    for (let i = n - 1; i >= 0; --i) {
        f[i] = cost[i] + Math.min(f[i + 1], f[i + 2]);
    }
    return Math.min(f[0], f[1]);
}
