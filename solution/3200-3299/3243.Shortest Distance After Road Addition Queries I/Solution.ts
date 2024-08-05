function shortestDistanceAfterQueries(n: number, queries: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; ++i) {
        g[i].push(i + 1);
    }
    const bfs = (i: number): number => {
        const q: number[] = [i];
        const vis: boolean[] = Array(n).fill(false);
        vis[i] = true;
        for (let d = 0; ; ++d) {
            const nq: number[] = [];
            for (const u of q) {
                if (u === n - 1) {
                    return d;
                }
                for (const v of g[u]) {
                    if (!vis[v]) {
                        vis[v] = true;
                        nq.push(v);
                    }
                }
            }
            q.splice(0, q.length, ...nq);
        }
    };
    const ans: number[] = [];
    for (const [u, v] of queries) {
        g[u].push(v);
        ans.push(bfs(0));
    }
    return ans;
}
