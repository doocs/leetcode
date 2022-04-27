function knightProbability(
    n: number,
    k: number,
    row: number,
    column: number,
): number {
    let dp = Array.from({ length: k + 1 }, v =>
        Array.from({ length: n }, w => new Array(n).fill(0)),
    );
    const directions = [
        [-2, -1],
        [-2, 1],
        [-1, -2],
        [-1, 2],
        [1, -2],
        [1, 2],
        [2, -1],
        [2, 1],
    ];
    for (let depth = 0; depth <= k; depth++) {
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < n; j++) {
                if (!depth) {
                    dp[depth][i][j] = 1;
                } else {
                    for (let [dx, dy] of directions) {
                        let [x, y] = [i + dx, j + dy];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            dp[depth][i][j] += dp[depth - 1][x][y] / 8;
                        }
                    }
                }
            }
        }
    }
    return dp[k][row][column];
}
