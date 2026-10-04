function placedCoins(edges: number[][], cost: number[]): number[] {
    const n = cost.length;
    const ans: number[] = Array(n).fill(1);
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const sub: number[][] = Array(n);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [a, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([a, fa, 1]);
            for (const b of g[a]) {
                if (b !== fa) {
                    stk.push([b, a, 0]);
                }
            }
        } else {
            const res: number[] = [cost[a]];
            for (const b of g[a]) {
                if (b !== fa) {
                    res.push(...sub[b]);
                }
            }
            res.sort((x, y) => x - y);
            const m = res.length;
            if (m >= 3) {
                const x = res[m - 1] * res[m - 2] * res[m - 3];
                const y = res[0] * res[1] * res[m - 1];
                ans[a] = Math.max(0, x, y);
            }
            if (m > 5) {
                sub[a] = [res[0], res[1], res[m - 3], res[m - 2], res[m - 1]];
            } else {
                sub[a] = res;
            }
        }
    }
    return ans;
}
