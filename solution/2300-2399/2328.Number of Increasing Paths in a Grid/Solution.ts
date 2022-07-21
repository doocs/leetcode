function countPaths(grid: number[][]): number {
    const mod = BigInt(10 ** 9 + 7);
    const dirs = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    const m = grid.length,
        n = grid[0].length;
    const dp = Array.from({ length: m }, v => new Array(n).fill(-1n));

    function dfs(x, y) {
        if (dp[x][y] != -1) return dp[x][y];
        let count = 1n;
        for (let [dx, dy] of dirs) {
            let i = x + dx,
                j = y + dy;
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] <= grid[x][y])
                continue;
            count = (count + dfs(i, j)) % mod;
        }
        dp[x][y] = count;
        return count;
    }

    let sum = 0n;
    for (let i = 0; i < m; i++) {
        for (let j = 0; j < n; j++) {
            sum = (sum + dfs(i, j)) % mod;
        }
    }
    return Number(sum);
}
