function evenSumSubgraphs(nums: number[], edges: number[][]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const m = (1 << n) - 1;
    let ans = 0;
    let vis = 0;

    const dfs = (u: number): void => {
        vis |= 1 << u;
        for (const v of g[u]) {
            if (((vis >> v) & 1) === 0) {
                dfs(v);
            }
        }
    };

    for (let sub = 1; sub <= m; sub++) {
        let s = 0;
        for (let i = 0; i < n; i++) {
            if ((sub >> i) & 1) {
                s += nums[i];
            }
        }
        if (s % 2 !== 0) {
            continue;
        }
        vis = m ^ sub;
        dfs(sub.toString(2).length - 1);
        if (vis === m) {
            ans++;
        }
    }
    return ans;
}
