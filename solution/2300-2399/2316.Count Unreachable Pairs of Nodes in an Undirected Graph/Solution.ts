function countPairs(n: number, edges: number[][]): number {
    const g = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis = new Array(n).fill(false);
    const dfs = (i: number) => {
        vis[i] = true;
        let cnt = 1;
        for (const j of g[i]) {
            if (!vis[j]) {
                cnt += dfs(j);
            }
        }
        return cnt;
    };
    let ans = 0;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        if (!vis[i]) {
            const t = dfs(i);
            ans += s * t;
            s += t;
        }
    }
    return ans;
}
