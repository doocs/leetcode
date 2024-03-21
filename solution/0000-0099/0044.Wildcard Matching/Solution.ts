function isMatch(s: string, p: string): boolean {
    const m = s.length;
    const n = p.length;
    const f: number[][] = Array.from({ length: m + 1 }, () =>
        Array.from({ length: n + 1 }, () => -1),
    );
    const dfs = (i: number, j: number): boolean => {
        if (i >= m) {
            return j >= n || (p[j] === '*' && dfs(i, j + 1));
        }
        if (j >= n) {
            return false;
        }
        if (f[i][j] !== -1) {
            return f[i][j] === 1;
        }
        if (p[j] === '*') {
            f[i][j] = dfs(i + 1, j) || dfs(i, j + 1) ? 1 : 0;
        } else {
            f[i][j] = (p[j] === '?' || s[i] === p[j]) && dfs(i + 1, j + 1) ? 1 : 0;
        }
        return f[i][j] === 1;
    };
    return dfs(0, 0);
}
