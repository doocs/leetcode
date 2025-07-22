function countSquares(matrix: number[][]): number {
    const [m, n] = [matrix.length, matrix[0].length];
    const f = Array.from({ length: m }, () => Array(n));
    const dfs = (i: number, j: number): number => {
        if (i === m || j === n || !matrix[i][j]) return 0;
        f[i][j] ??= 1 + Math.min(dfs(i + 1, j), dfs(i, j + 1), dfs(i + 1, j + 1));
        return f[i][j];
    };
    let ans = 0;

    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            ans += dfs(i, j);
        }
    }

    return ans;
}
