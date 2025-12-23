function findSpecialNodes(n: number, edges: number[][]): string {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }

    const bfs = (start: number): [number, number[]] => {
        const dist = new Array<number>(n).fill(-1);
        dist[start] = 0;
        const q: number[] = [start];
        let far = start;

        for (const u of q) {
            if (dist[u] > dist[far]) {
                far = u;
            }
            for (const v of g[u]) {
                if (dist[v] === -1) {
                    dist[v] = dist[u] + 1;
                    q.push(v);
                }
            }
        }
        return [far, dist];
    };

    const [a] = bfs(0);
    const [b, dist1] = bfs(a);
    const [, dist2] = bfs(b);
    const d = dist1[b];

    const ans: string[] = new Array(n).fill('0');
    for (let i = 0; i < n; i++) {
        if (dist1[i] === d || dist2[i] === d) {
            ans[i] = '1';
        }
    }
    return ans.join('');
}
