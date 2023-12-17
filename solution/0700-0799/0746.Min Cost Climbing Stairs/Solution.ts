function minCostClimbingStairs(cost: number[]): number {
    let [f, g] = [0, 0];
    for (let i = 2; i <= cost.length; ++i) {
        [f, g] = [g, Math.min(f + cost[i - 2], g + cost[i - 1])];
    }
    return g;
}
