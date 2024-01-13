function superEggDrop(k: number, n: number): number {
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(k + 1).fill(0));
    for (let i = 1; i <= n; ++i) {
        f[i][1] = i;
    }
    for (let i = 1; i <= n; ++i) {
        for (let j = 2; j <= k; ++j) {
            let l = 1;
            let r = i;
            while (l < r) {
                const mid = (l + r + 1) >> 1;
                const a = f[mid - 1][j - 1];
                const b = f[i - mid][j];
                if (a <= b) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            f[i][j] = Math.max(f[l - 1][j - 1], f[i - l][j]) + 1;
        }
    }
    return f[n][k];
}
