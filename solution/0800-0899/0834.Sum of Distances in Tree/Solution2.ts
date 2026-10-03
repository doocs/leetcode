function sumOfDistancesInTree(n: number, edges: number[][]): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const ans: number[] = new Array(n).fill(0);
    const size: number[] = new Array(n).fill(0);
    const stk: number[][] = [[0, -1, 0, 0]];
    while (stk.length) {
        const [i, fa, d, state] = stk.pop()!;
        if (state === 0) {
            ans[0] += d;
            stk.push([i, fa, d, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, d + 1, 0]);
                }
            }
        } else {
            size[i] = 1;
            for (const j of g[i]) {
                if (j !== fa) {
                    size[i] += size[j];
                }
            }
        }
    }
    const walk: number[][] = [[0, -1, ans[0]]];
    while (walk.length) {
        const [i, fa, t] = walk.pop()!;
        ans[i] = t;
        for (const j of g[i]) {
            if (j !== fa) {
                walk.push([j, i, t - size[j] + n - size[j]]);
            }
        }
    }
    return ans;
}
