function minimumFuelCost(roads: number[][], seats: number): number {
    const n = roads.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of roads) {
        g[a].push(b);
        g[b].push(a);
    }
    let ans = 0;
    const dfs = (a: number, fa: number): number => {
        let sz = 1;
        for (const b of g[a]) {
            if (b !== fa) {
                const t = dfs(b, a);
                ans += Math.ceil(t / seats);
                sz += t;
            }
        }
        return sz;
    };
    dfs(0, -1);
    return ans;
}
