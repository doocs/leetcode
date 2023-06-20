function pondSizes(land: number[][]): number[] {
    const m = land.length;
    const n = land[0].length;
    const dfs = (i: number, j: number): number => {
        let res = 1;
        land[i][j] = 1;
        for (let x = i - 1; x <= i + 1; ++x) {
            for (let y = j - 1; y <= j + 1; ++y) {
                if (x >= 0 && x < m && y >= 0 && y < n && land[x][y] === 0) {
                    res += dfs(x, y);
                }
            }
        }
        return res;
    };
    const ans: number[] = [];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (land[i][j] === 0) {
                ans.push(dfs(i, j));
            }
        }
    }
    ans.sort((a, b) => a - b);
    return ans;
}
