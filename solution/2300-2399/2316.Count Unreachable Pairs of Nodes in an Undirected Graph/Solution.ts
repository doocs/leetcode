function countPairs(n: number, edges: number[][]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number): number => {
        if (vis[i]) {
            return 0;
        }
        vis[i] = true;
        let cnt = 1;
        for (const j of g[i]) {
            cnt += dfs(j);
        }
        return cnt;
    };
    let [ans, s] = [0, 0];
    for (let i = 0; i < n; ++i) {
        const t = dfs(i);
        ans += s * t;
        s += t;
    }
    return ans;
}
