function minimumTotalPrice(
    n: number,
    edges: number[][],
    price: number[],
    trips: number[][],
): number {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of edges) {
        g[a].push(b);
        g[b].push(a);
    }
    const cnt: number[] = new Array(n).fill(0);
    const dfs = (i: number, fa: number, k: number): boolean => {
        ++cnt[i];
        if (i === k) {
            return true;
        }
        let ok = false;
        for (const j of g[i]) {
            if (j !== fa) {
                ok = dfs(j, i, k);
                if (ok) {
                    break;
                }
            }
        }
        if (!ok) {
            --cnt[i];
        }
        return ok;
    };
    for (const [start, end] of trips) {
        dfs(start, -1, end);
    }
    const dfs2 = (i: number, fa: number): number[] => {
        let a: number = price[i] * cnt[i];
        let b: number = a >> 1;
        for (const j of g[i]) {
            if (j !== fa) {
                const [x, y] = dfs2(j, i);
                a += Math.min(x, y);
                b += x;
            }
        }
        return [a, b];
    };
    const [a, b] = dfs2(0, -1);
    return Math.min(a, b);
}
