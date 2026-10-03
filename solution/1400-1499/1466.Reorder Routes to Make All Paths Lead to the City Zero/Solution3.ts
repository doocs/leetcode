function minReorder(n: number, connections: number[][]): number {
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (const [a, b] of connections) {
        g[a].push([b, 1]);
        g[b].push([a, 0]);
    }
    let ans = 0;
    const stk: [number, number][] = [[0, -1]];
    while (stk.length) {
        const [a, fa] = stk.pop()!;
        for (const [b, c] of g[a]) {
            if (b !== fa) {
                ans += c;
                stk.push([b, a]);
            }
        }
    }
    return ans;
}
