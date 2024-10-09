function maxScore(a: number[], b: number[]): number {
    const [m, n] = [a.length, b.length];
    const f: number[][] = Array.from({ length: m }, () => Array(n).fill(-1));
    const dfs = (i: number, j: number): number => {
        if (j >= n) {
            return i >= m ? 0 : -Infinity;
        }
        if (i >= m) {
            return 0;
        }
        if (f[i][j] !== -1) {
            return f[i][j];
        }
        return (f[i][j] = Math.max(dfs(i, j + 1), a[i] * b[j] + dfs(i + 1, j + 1)));
    };
    return dfs(0, 0);
}
