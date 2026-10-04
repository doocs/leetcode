function countGoodNodes(edges: number[][]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    let ans = 0;
    const sz: number[] = Array(n).fill(0);
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
            let pre = -1;
            let cnt = 1;
            let ok = 1;
            for (const b of g[a]) {
                if (b !== fa) {
                    const cur = sz[b];
                    cnt += cur;
                    if (pre < 0) {
                        pre = cur;
                    } else if (pre !== cur) {
                        ok = 0;
                    }
                }
            }
            ans += ok;
            sz[a] = cnt;
        }
    }
    return ans;
}
