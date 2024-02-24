function minIncrements(n: number, cost: number[]): number {
    let ans = 0;
    for (let i = n >> 1; i; --i) {
        const [l, r] = [i << 1, (i << 1) | 1];
        ans += Math.abs(cost[l - 1] - cost[r - 1]);
        cost[i - 1] += Math.max(cost[l - 1], cost[r - 1]);
    }
    return ans;
}
