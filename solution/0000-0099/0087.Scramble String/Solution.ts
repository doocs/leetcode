function isScramble(s1: string, s2: string): boolean {
    const n = s1.length;
    const f = new Array(n)
        .fill(0)
        .map(() => new Array(n).fill(0).map(() => new Array(n + 1).fill(-1)));
    const dfs = (i: number, j: number, k: number): boolean => {
        if (f[i][j][k] !== -1) {
            return f[i][j][k] === 1;
        }
        if (k === 1) {
            return s1[i] === s2[j];
        }
        for (let h = 1; h < k; ++h) {
            if (dfs(i, j, h) && dfs(i + h, j + h, k - h)) {
                return Boolean((f[i][j][k] = 1));
            }
            if (dfs(i + h, j, k - h) && dfs(i, j + k - h, h)) {
                return Boolean((f[i][j][k] = 1));
            }
        }
        return Boolean((f[i][j][k] = 0));
    };
    return dfs(0, 0, n);
}
