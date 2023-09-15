function mostSimilar(
    n: number,
    roads: number[][],
    names: string[],
    targetPath: string[],
): number[] {
    const g: number[][] = Array.from({ length: n }, () => []);
    for (const [a, b] of roads) {
        g[a].push(b);
        g[b].push(a);
    }
    const m = targetPath.length;
    const f = Array.from({ length: m }, () => Array.from({ length: n }, () => Infinity));
    const pre: number[][] = Array.from({ length: m }, () => Array.from({ length: n }, () => -1));
    for (let j = 0; j < n; ++j) {
        f[0][j] = names[j] === targetPath[0] ? 0 : 1;
    }
    for (let i = 1; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            for (const k of g[j]) {
                const t = f[i - 1][k] + (names[j] === targetPath[i] ? 0 : 1);
                if (t < f[i][j]) {
                    f[i][j] = t;
                    pre[i][j] = k;
                }
            }
        }
    }
    let k = 0;
    let mi = Infinity;
    for (let j = 0; j < n; ++j) {
        if (f[m - 1][j] < mi) {
            mi = f[m - 1][j];
            k = j;
        }
    }
    const ans: number[] = Array(m).fill(0);
    for (let i = m - 1; ~i; --i) {
        ans[i] = k;
        k = pre[i][k];
    }
    return ans;
}
