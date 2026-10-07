function minEdgeReversals(n: number, edges: number[][]): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (const [x, y] of edges) {
        g[x].push([y, 1]);
        g[y].push([x, -1]);
    }
    const ans: number[] = Array(n).fill(0);
    const stk: [number, number][] = [[0, -1]];
    while (stk.length) {
        const [i, fa] = stk.pop()!;
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[0] += k < 0 ? 1 : 0;
                stk.push([j, i]);
            }
        }
    }
    stk.push([0, -1]);
    while (stk.length) {
        const [i, fa] = stk.pop()!;
        for (const [j, k] of g[i]) {
            if (j !== fa) {
                ans[j] = ans[i] + k;
                stk.push([j, i]);
            }
        }
    }
    return ans;
}
