function minimumScore(nums: number[], edges: number[][]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const s = nums.reduce((a, b) => a ^ b, 0);
    let s1 = 0;
    let ans = Number.MAX_SAFE_INTEGER;
    function dfs(i: number, fa: number): number {
        let res = nums[i];
        for (const j of g[i]) {
            if (j !== fa) {
                res ^= dfs(j, i);
            }
        }
        return res;
    }
    function dfs2(i: number, fa: number): number {
        let res = nums[i];
        for (const j of g[i]) {
            if (j !== fa) {
                const s2 = dfs2(j, i);
                res ^= s2;
                const mx = Math.max(s ^ s1, s2, s1 ^ s2);
                const mn = Math.min(s ^ s1, s2, s1 ^ s2);
                ans = Math.min(ans, mx - mn);
            }
        }
        return res;
    }
    for (let i = 0; i < n; ++i) {
        for (const j of g[i]) {
            s1 = dfs(i, j);
            dfs2(i, j);
        }
    }
    return ans;
}
