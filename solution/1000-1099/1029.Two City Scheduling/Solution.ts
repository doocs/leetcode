function twoCitySchedCost(costs: number[][]): number {
    costs.sort((a, b) => a[0] - a[1] - (b[0] - b[1]));
    const n = costs.length >> 1;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans += costs[i][0] + costs[i + n][1];
    }
    return ans;
}
