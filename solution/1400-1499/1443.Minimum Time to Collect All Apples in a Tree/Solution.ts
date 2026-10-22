function minTime(n: number, edges: number[][], hasApple: boolean[]): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u].push(v);
        g[v].push(u);
    }
    const cost = Array(n).fill(0);
    const stk: [number, number, number][] = [[0, -1, 0]];
    while (stk.length) {
        const [u, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([u, fa, 1]);
            for (const v of g[u]) {
                if (v !== fa) {
                    stk.push([v, u, 0]);
                }
            }
        } else {
            let next = 0;
            for (const v of g[u]) {
                if (v !== fa) {
                    next += cost[v];
                }
            }
            if (hasApple[u] || next > 0) {
                cost[u] = next + (u === 0 ? 0 : 2);
            }
        }
    }
    return cost[0];
}
