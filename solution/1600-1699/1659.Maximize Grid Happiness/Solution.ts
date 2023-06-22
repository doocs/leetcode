function getMaxGridHappiness(
    m: number,
    n: number,
    introvertsCount: number,
    extrovertsCount: number,
): number {
    const p = 3 ** (n - 1);
    const h: number[][] = [
        [0, 0, 0],
        [0, -60, -10],
        [0, -10, 40],
    ];
    const memo: number[][][][] = Array(m * n)
        .fill(0)
        .map(() =>
            Array(p * 3)
                .fill(0)
                .map(() =>
                    Array(introvertsCount + 1)
                        .fill(0)
                        .map(() => Array(extrovertsCount + 1).fill(-1)),
                ),
        );
    const dfs = (pos: number, pre: number, ic: number, ec: number): number => {
        if (pos === m * n || (ic === 0 && ec === 0)) {
            return 0;
        }
        if (memo[pos][pre][ic][ec] !== -1) {
            return memo[pos][pre][ic][ec];
        }
        let ans = 0;
        const up = Math.floor(pre / p);
        const left = pos % n == 0 ? 0 : pre % 3;
        for (let i = 0; i < 3; ++i) {
            if ((i === 1 && ic === 0) || (i === 2 && ec === 0)) {
                continue;
            }
            const cur = (pre % p) * 3 + i;
            const a = h[up][i] + h[left][i];
            const nic = i === 1 ? ic - 1 : ic;
            const nec = i === 2 ? ec - 1 : ec;
            const b = dfs(pos + 1, cur, nic, nec);
            const c = i === 1 ? 120 : i === 2 ? 40 : 0;
            ans = Math.max(ans, a + b + c);
        }
        return (memo[pos][pre][ic][ec] = ans);
    };
    return dfs(0, 0, introvertsCount, extrovertsCount);
}
