function maximumScoreAfterOperations(edges: number[][], values: number[]): number {
    const n = values.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const sum = Array(n).fill(0);
    const best = Array(n).fill(0);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, fa, 1]);
            for (const j of g[i]) {
                if (j !== fa) {
                    stk.push([j, i, 0]);
                }
            }
        } else {
            let a = 0;
            let b = 0;
            let leaf = true;
            for (const j of g[i]) {
                if (j !== fa) {
                    leaf = false;
                    a += sum[j];
                    b += best[j];
                }
            }
            if (leaf) {
                sum[i] = values[i];
                best[i] = 0;
            } else {
                sum[i] = values[i] + a;
                best[i] = Math.max(values[i] + b, a);
            }
        }
    }
    return best[0];
}
