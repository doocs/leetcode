function minCostClimbingStairs(cost: number[]): number {
    const n = cost.length;
    const cache = Array(n).fill(-1);

    const fn = (i: number): number => {
        if (i <= 1) return cost[i];
        if (cache[i] !== undefined && cache[i] !== -1) return cache[i];

        cache[i] = (cost[i] ?? 0) + Math.min(fn(i - 1), fn(i - 2));

        return cache[i];
    };

    return fn(n);
}
