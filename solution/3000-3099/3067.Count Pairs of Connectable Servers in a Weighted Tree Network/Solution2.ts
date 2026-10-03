function countPairsOfConnectableServers(edges: number[][], signalSpeed: number): number[] {
    const n = edges.length + 1;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b, w] of edges) {
        g[a].push([b, w]);
        g[b].push([a, w]);
    }
    const count = (start: number, fa: number, dist: number): number => {
        let cnt = 0;
        const stk: number[][] = [[start, fa, dist]];
        while (stk.length) {
            const [a, parent, ws] = stk.pop()!;
            if (ws % signalSpeed === 0) {
                cnt++;
            }
            for (const [b, w] of g[a]) {
                if (b !== parent) {
                    stk.push([b, a, ws + w]);
                }
            }
        }
        return cnt;
    };
    const ans: number[] = Array(n).fill(0);
    for (let a = 0; a < n; ++a) {
        let s = 0;
        for (const [b, w] of g[a]) {
            const t = count(b, a, w);
            ans[a] += s * t;
            s += t;
        }
    }
    return ans;
}
