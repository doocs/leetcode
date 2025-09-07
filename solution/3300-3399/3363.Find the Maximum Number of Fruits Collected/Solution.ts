function maxCollectedFruits(fruits: number[][]): number {
    const n = fruits.length;
    const inf = 1 << 29;
    const f: number[][] = Array.from({ length: n }, () => Array(n).fill(-inf));

    f[0][n - 1] = fruits[0][n - 1];
    for (let i = 1; i < n; i++) {
        for (let j = i + 1; j < n; j++) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - 1]) + fruits[i][j];
            if (j + 1 < n) {
                f[i][j] = Math.max(f[i][j], f[i - 1][j + 1] + fruits[i][j]);
            }
        }
    }

    f[n - 1][0] = fruits[n - 1][0];
    for (let j = 1; j < n; j++) {
        for (let i = j + 1; i < n; i++) {
            f[i][j] = Math.max(f[i][j - 1], f[i - 1][j - 1]) + fruits[i][j];
            if (i + 1 < n) {
                f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + fruits[i][j]);
            }
        }
    }

    let ans = f[n - 2][n - 1] + f[n - 1][n - 2];
    for (let i = 0; i < n; i++) {
        ans += fruits[i][i];
    }

    return ans;
}
