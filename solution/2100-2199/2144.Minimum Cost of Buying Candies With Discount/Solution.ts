function minimumCost(cost: number[]): number {
    cost.sort((a, b) => a - b);
    let ans = 0;
    for (let i = cost.length - 1; i >= 0; i -= 3) {
        ans += cost[i];
        if (i) {
            ans += cost[i - 1];
        }
    }
    return ans;
}
