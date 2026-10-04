function minimumFuelCost(roads: number[][], seats: number): number {
    const n = roads.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of roads) {
        g[a].push(b);
        g[b].push(a);
    }
    let ans = 0;
    const sz: number[] = Array(n).fill(1);
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
            for (const b of g[a]) {
                if (b !== fa) {
                    const t = sz[b];
                    ans += Math.ceil(t / seats);
                    sz[a] += t;
                }
            }
        }
    }
    return ans;
}
