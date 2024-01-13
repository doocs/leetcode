function isInterleave(s1: string, s2: string, s3: string): boolean {
    const m = s1.length;
    const n = s2.length;
    if (m + n !== s3.length) {
        return false;
    }
    const f: number[][] = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    const dfs = (i: number, j: number): boolean => {
        if (i >= m && j >= n) {
            return true;
        }
        if (f[i][j]) {
            return f[i][j] === 1;
        }
        f[i][j] = -1;
        if (i < m && s1[i] === s3[i + j] && dfs(i + 1, j)) {
            f[i][j] = 1;
        }
        if (f[i][j] === -1 && j < n && s2[j] === s3[i + j] && dfs(i, j + 1)) {
            f[i][j] = 1;
        }
        return f[i][j] === 1;
    };
    return dfs(0, 0);
}
