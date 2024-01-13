function getMaxGridHappiness(
    m: number,
    n: number,
    introvertsCount: number,
    extrovertsCount: number,
): number {
    const mx = 3 ** n;
    const f: number[] = Array(mx).fill(0);
    const g: number[][] = Array(mx)
        .fill(0)
        .map(() => Array(mx).fill(0));
    const h: number[][] = [
        [0, 0, 0],
        [0, -60, -10],
        [0, -10, 40],
    ];
    const bits: number[][] = Array(mx)
        .fill(0)
        .map(() => Array(n).fill(0));
    const ix: number[] = Array(mx).fill(0);
    const ex: number[] = Array(mx).fill(0);
    const memo: number[][][][] = Array(m)
        .fill(0)
        .map(() =>
            Array(mx)
                .fill(0)
                .map(() =>
                    Array(introvertsCount + 1)
                        .fill(0)
                        .map(() => Array(extrovertsCount + 1).fill(-1)),
                ),
        );
    for (let i = 0; i < mx; ++i) {
        let mask = i;
        for (let j = 0; j < n; ++j) {
            const x = mask % 3;
            mask = Math.floor(mask / 3);
            bits[i][j] = x;
            if (x === 1) {
                ix[i] += 1;
                f[i] += 120;
            } else if (x === 2) {
                ex[i] += 1;
                f[i] += 40;
            }
            if (j > 0) {
                f[i] += h[x][bits[i][j - 1]];
            }
        }
    }
    for (let i = 0; i < mx; ++i) {
        for (let j = 0; j < mx; ++j) {
            for (let k = 0; k < n; ++k) {
                g[i][j] += h[bits[i][k]][bits[j][k]];
            }
        }
    }
    const dfs = (i: number, pre: number, ic: number, ec: number): number => {
        if (i === m || (ic === 0 && ec === 0)) {
            return 0;
        }
        if (memo[i][pre][ic][ec] !== -1) {
            return memo[i][pre][ic][ec];
        }
        let ans = 0;
        for (let cur = 0; cur < mx; ++cur) {
            if (ix[cur] <= ic && ex[cur] <= ec) {
                const a = f[cur] + g[pre][cur];
                const b = dfs(i + 1, cur, ic - ix[cur], ec - ex[cur]);
                ans = Math.max(ans, a + b);
            }
        }
        return (memo[i][pre][ic][ec] = ans);
    };
    return dfs(0, 0, introvertsCount, extrovertsCount);
}
