function rootCount(edges: number[][], guesses: number[][], k: number): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    const gs: Map<number, number> = new Map();
    const f = (i: number, j: number) => i * n + j;
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    for (const [a, b] of guesses) {
        const x = f(a, b);
        gs.set(x, (gs.get(x) || 0) + 1);
    }
    let cnt = 0;
    const stk: number[][] = [[0, -1]];
    while (stk.length) {
        const [i, fa] = stk.pop()!;
        for (const j of g[i]) {
            if (j !== fa) {
                cnt += gs.get(f(i, j)) || 0;
                stk.push([j, i]);
            }
        }
    }
    let ans = 0;
    const walk: number[][] = [[0, -1, cnt]];
    while (walk.length) {
        const [i, fa, c] = walk.pop()!;
        if (c >= k) {
            ans++;
        }
        for (const j of g[i]) {
            if (j !== fa) {
                const a = gs.get(f(i, j)) || 0;
                const b = gs.get(f(j, i)) || 0;
                walk.push([j, i, c - a + b]);
            }
        }
    }
    return ans;
}
