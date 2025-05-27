function minCuttingCost(n: number, m: number, k: number): number {
    const x = Math.max(n, m);
    return x <= k ? 0 : k * (x - k);
}
