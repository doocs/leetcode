function maxCoins(lane1: number[], lane2: number[]): number {
    const n = lane1.length;
    const f: number[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: 2 }, () => Array(3).fill(0)),
    );
    for (let i = n - 1; i >= 0; --i) {
        for (let k = 0; k < 3; ++k) {
            for (let j = 0; j < 2; ++j) {
                const x = j === 0 ? lane1[i] : lane2[i];
                let ans = Math.max(x, f[i + 1][j][k] + x);
                if (k > 0) {
                    ans = Math.max(ans, f[i + 1][j ^ 1][k - 1] + x, f[i][j ^ 1][k - 1]);
                }
                f[i][j][k] = ans;
            }
        }
    }
    let ans = f[0][0][2];
    for (let i = 1; i < n; ++i) {
        ans = Math.max(ans, f[i][0][2]);
    }
    return ans;
}
