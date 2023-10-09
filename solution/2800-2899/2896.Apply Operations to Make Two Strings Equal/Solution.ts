function minOperations(s1: string, s2: string, x: number): number {
    const idx: number[] = [];
    for (let i = 0; i < s1.length; ++i) {
        if (s1[i] !== s2[i]) {
            idx.push(i);
        }
    }
    const m = idx.length;
    if (m % 2 === 1) {
        return -1;
    }
    if (m === 0) {
        return 0;
    }
    const f: number[][] = Array.from({ length: m }, () => Array.from({ length: m }, () => -1));
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        f[i][j] = dfs(i + 1, j - 1) + x;
        f[i][j] = Math.min(f[i][j], dfs(i + 2, j) + idx[i + 1] - idx[i]);
        f[i][j] = Math.min(f[i][j], dfs(i, j - 2) + idx[j] - idx[j - 1]);
        return f[i][j];
    };
    return dfs(0, m - 1);
}
