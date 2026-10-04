function maximumPoints(edges: number[][], coins: number[], k: number): number {
    const n = coins.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const f: number[][] = Array.from({ length: n }, () => Array(15).fill(0));
    const stk: [number, number, number][] = [[0, -1, 0]];
    while (stk.length) {
        const [i, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([i, fa, 1]);
            for (const c of g[i]) {
                if (c !== fa) {
                    stk.push([c, i, 0]);
                }
            }
        } else {
            for (let j = 0; j < 15; ++j) {
                let a = (coins[i] >> j) - k;
                let b = coins[i] >> (j + 1);
                for (const c of g[i]) {
                    if (c !== fa) {
                        a += f[c][j];
                        if (j < 14) {
                            b += f[c][j + 1];
                        }
                    }
                }
                f[i][j] = Math.max(a, b);
            }
        }
    }
    return f[0][0];
}
