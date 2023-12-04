function minReorder(n: number, connections: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b] of connections) {
        g[a].push([b, 1]);
        g[b].push([a, 0]);
    }
    const dfs = (a: number, fa: number): number => {
        let ans = 0;
        for (const [b, c] of g[a]) {
            if (b !== fa) {
                ans += c + dfs(b, a);
            }
        }
        return ans;
    };
    return dfs(0, -1);
}
