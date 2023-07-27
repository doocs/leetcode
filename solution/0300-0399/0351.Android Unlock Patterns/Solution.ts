function numberOfPatterns(m: number, n: number): number {
    const cross: number[][] = Array(10)
        .fill(0)
        .map(() => Array(10).fill(0));
    const vis: boolean[] = Array(10).fill(false);
    cross[1][3] = cross[3][1] = 2;
    cross[1][7] = cross[7][1] = 4;
    cross[1][9] = cross[9][1] = 5;
    cross[2][8] = cross[8][2] = 5;
    cross[3][7] = cross[7][3] = 5;
    cross[3][9] = cross[9][3] = 6;
    cross[4][6] = cross[6][4] = 5;
    cross[7][9] = cross[9][7] = 8;
    const dfs = (i: number, cnt: number): number => {
        if (cnt > n) {
            return 0;
        }
        vis[i] = true;
        let ans = 0;
        if (cnt >= m) {
            ++ans;
        }
        for (let j = 1; j < 10; ++j) {
            const x = cross[i][j];
            if (!vis[j] && (x === 0 || vis[x])) {
                ans += dfs(j, cnt + 1);
            }
        }
        vis[i] = false;
        return ans;
    };
    return dfs(1, 1) * 4 + dfs(2, 1) * 4 + dfs(5, 1);
}
