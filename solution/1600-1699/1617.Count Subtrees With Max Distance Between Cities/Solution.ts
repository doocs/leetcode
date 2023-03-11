function countSubgraphsForEachDiameter(n: number, edges: number[][]): number[] {
    const g = Array.from({ length: n }, () => []);
    for (const [u, v] of edges) {
        g[u - 1].push(v - 1);
        g[v - 1].push(u - 1);
    }
    const ans: number[] = new Array(n - 1).fill(0);
    let [mx, msk, nxt] = [0, 0, 0];
    const dfs = (u: number, d: number) => {
        if (mx < d) {
            mx = d;
            nxt = u;
        }
        msk ^= 1 << u;
        for (const v of g[u]) {
            if ((msk >> v) & 1) {
                dfs(v, d + 1);
            }
        }
    };
    for (let mask = 1; mask < 1 << n; ++mask) {
        if ((mask & (mask - 1)) === 0) {
            continue;
        }
        msk = mask;
        mx = 0;
        const cur = 31 - numberOfLeadingZeros(msk);
        dfs(cur, 0);
        if (msk === 0) {
            msk = mask;
            mx = 0;
            dfs(nxt, 0);
            ++ans[mx - 1];
        }
    }
    return ans;
}

function numberOfLeadingZeros(i: number): number {
    if (i == 0) return 32;
    let n = 1;
    if (i >>> 16 == 0) {
        n += 16;
        i <<= 16;
    }
    if (i >>> 24 == 0) {
        n += 8;
        i <<= 8;
    }
    if (i >>> 28 == 0) {
        n += 4;
        i <<= 4;
    }
    if (i >>> 30 == 0) {
        n += 2;
        i <<= 2;
    }
    n -= i >>> 31;
    return n;
}
