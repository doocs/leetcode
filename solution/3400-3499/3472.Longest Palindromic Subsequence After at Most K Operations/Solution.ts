function longestPalindromicSubsequence(s: string, k: number): number {
    const n = s.length;
    const sCodes = s.split('').map(c => c.charCodeAt(0));
    const f: number[][][] = Array.from({ length: n }, () =>
        Array.from({ length: n }, () => Array(k + 1).fill(-1)),
    );

    function dfs(i: number, j: number, k: number): number {
        if (i > j) {
            return 0;
        }
        if (i === j) {
            return 1;
        }

        if (f[i][j][k] !== -1) {
            return f[i][j][k];
        }

        let res = Math.max(dfs(i + 1, j, k), dfs(i, j - 1, k));
        const d = Math.abs(sCodes[i] - sCodes[j]);
        const t = Math.min(d, 26 - d);
        if (t <= k) {
            res = Math.max(res, 2 + dfs(i + 1, j - 1, k - t));
        }
        return (f[i][j][k] = res);
    }

    return dfs(0, n - 1, k);
}
