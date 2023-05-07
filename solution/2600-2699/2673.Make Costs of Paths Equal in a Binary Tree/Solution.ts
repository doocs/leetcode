function minIncrements(n: number, cost: number[]): number {
    let ans = 0;
    const dfs = (i: number): number => {
        if (i << 1 > n) {
            return cost[i - 1];
        }
        const [a, b] = [dfs(i << 1), dfs((i << 1) | 1)];
        ans += Math.max(a, b) - Math.min(a, b);
        return cost[i - 1] + Math.max(a, b);
    };
    dfs(1);
    return ans;
}
