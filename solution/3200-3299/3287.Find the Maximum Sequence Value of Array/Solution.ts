function maxValue(nums: number[], k: number): number {
    const m = 1 << 7;
    const n = nums.length;

    const f: boolean[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 2 }, () => Array(m).fill(false)),
    );
    f[0][0][0] = true;

    for (let i = 0; i < n; i++) {
        for (let j = 0; j <= k; j++) {
            for (let x = 0; x < m; x++) {
                if (f[i][j][x]) {
                    f[i + 1][j][x] = true;
                    f[i + 1][j + 1][x | nums[i]] = true;
                }
            }
        }
    }

    const g: boolean[][][] = Array.from({ length: n + 1 }, () =>
        Array.from({ length: k + 2 }, () => Array(m).fill(false)),
    );
    g[n][0][0] = true;

    for (let i = n; i > 0; i--) {
        for (let j = 0; j <= k; j++) {
            for (let y = 0; y < m; y++) {
                if (g[i][j][y]) {
                    g[i - 1][j][y] = true;
                    g[i - 1][j + 1][y | nums[i - 1]] = true;
                }
            }
        }
    }

    let ans = 0;

    for (let i = k; i <= n - k; i++) {
        for (let x = 0; x < m; x++) {
            if (f[i][k][x]) {
                for (let y = 0; y < m; y++) {
                    if (g[i][k][y]) {
                        ans = Math.max(ans, x ^ y);
                    }
                }
            }
        }
    }

    return ans;
}
