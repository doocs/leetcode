function getAncestors(n: number, edges: number[][]): number[][] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
    }
    const ans: number[][] = Array.from({ length: n }, () => []);
    const bfs = (s: number) => {
        const q: number[] = [s];
        const vis: boolean[] = Array.from({ length: n }, () => false);
        vis[s] = true;
        while (q.length) {
            const i = q.pop()!;
            for (const j of g[i]) {
                if (!vis[j]) {
                    vis[j] = true;
                    ans[j].push(s);
                    q.push(j);
                }
            }
        }
    };
    for (let i = 0; i < n; ++i) {
        bfs(i);
    }
    return ans;
}
