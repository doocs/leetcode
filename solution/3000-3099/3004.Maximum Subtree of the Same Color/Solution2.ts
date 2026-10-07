function maximumSubtreeSize(edges: number[][], colors: number[]): number {
    const n = edges.length + 1;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const size: number[] = Array(n).fill(1);
    const ok: boolean[] = Array(n).fill(false);
    let ans = 0;
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
            let good = true;
            for (const b of g[a]) {
                if (b !== fa) {
                    good = good && colors[a] === colors[b] && ok[b];
                    size[a] += size[b];
                }
            }
            if (good) {
                ans = Math.max(ans, size[a]);
            }
            ok[a] = good;
        }
    }
    return ans;
}
