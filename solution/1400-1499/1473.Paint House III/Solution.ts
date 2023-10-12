function minCost(houses: number[], cost: number[][], m: number, n: number, target: number): number {
    const inf = 1 << 30;
    const f: number[][][] = new Array(m)
        .fill(0)
        .map(() => new Array(n + 1).fill(0).map(() => new Array(target + 1).fill(inf)));
    if (houses[0] === 0) {
        for (let j = 1; j <= n; ++j) {
            f[0][j][1] = cost[0][j - 1];
        }
    } else {
        f[0][houses[0]][1] = 0;
    }
    for (let i = 1; i < m; ++i) {
        if (houses[i] === 0) {
            for (let j = 1; j <= n; ++j) {
                for (let k = 1; k <= Math.min(target, i + 1); ++k) {
                    for (let j0 = 1; j0 <= n; ++j0) {
                        if (j0 === j) {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k] + cost[i][j - 1]);
                        } else {
                            f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j0][k - 1] + cost[i][j - 1]);
                        }
                    }
                }
            }
        } else {
            const j = houses[i];
            for (let k = 1; k <= Math.min(target, i + 1); ++k) {
                for (let j0 = 1; j0 <= n; ++j0) {
                    if (j0 === j) {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j][k]);
                    } else {
                        f[i][j][k] = Math.min(f[i][j][k], f[i - 1][j0][k - 1]);
                    }
                }
            }
        }
    }
    let ans = inf;
    for (let j = 1; j <= n; ++j) {
        ans = Math.min(ans, f[m - 1][j][target]);
    }
    return ans >= inf ? -1 : ans;
}
