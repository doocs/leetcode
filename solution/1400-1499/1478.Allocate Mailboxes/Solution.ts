function minDistance(houses: number[], k: number): number {
    houses.sort((a, b) => a - b);
    const n = houses.length;
    const g: number[][] = Array.from({ length: n }, () => Array(n).fill(0));

    for (let i = n - 2; i >= 0; i--) {
        for (let j = i + 1; j < n; j++) {
            g[i][j] = g[i + 1][j - 1] + houses[j] - houses[i];
        }
    }

    const inf = Number.POSITIVE_INFINITY;
    const f: number[][] = Array.from({ length: n }, () => Array(k + 1).fill(inf));

    for (let i = 0; i < n; i++) {
        f[i][1] = g[0][i];
    }

    for (let j = 2; j <= k; j++) {
        for (let i = j - 1; i < n; i++) {
            for (let p = i - 1; p >= 0; p--) {
                f[i][j] = Math.min(f[i][j], f[p][j - 1] + g[p + 1][i]);
            }
        }
    }

    return f[n - 1][k];
}
