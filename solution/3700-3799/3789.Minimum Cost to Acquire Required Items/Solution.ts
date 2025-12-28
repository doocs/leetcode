function minimumCost(
    cost1: number,
    cost2: number,
    costBoth: number,
    need1: number,
    need2: number,
): number {
    const a = need1 * cost1 + need2 * cost2;
    const b = costBoth * Math.max(need1, need2);
    const mn = Math.min(need1, need2);
    const c = costBoth * mn + (need1 - mn) * cost1 + (need2 - mn) * cost2;
    return Math.min(a, b, c);
}
