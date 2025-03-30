function minCosts(cost: number[]): number[] {
    const n = cost.length;
    const ans: number[] = Array(n).fill(0);
    let mi = cost[0];
    for (let i = 0; i < n; ++i) {
        mi = Math.min(mi, cost[i]);
        ans[i] = mi;
    }
    return ans;
}
