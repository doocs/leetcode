function highestPeak(isWater: number[][]): number[][] {
    const m = isWater.length;
    const n = isWater[0].length;
    let ans: number[][] = [];
    let q: number[][] = [];
    for (let i = 0; i < m; ++i) {
        ans.push(new Array(n).fill(-1));
        for (let j = 0; j < n; ++j) {
            if (isWater[i][j]) {
                q.push([i, j]);
                ans[i][j] = 0;
            }
        }
    }
    const dirs = [-1, 0, 1, 0, -1];
    while (q.length) {
        let tq: number[][] = [];
        for (const [i, j] of q) {
            for (let k = 0; k < 4; k++) {
                const [x, y] = [i + dirs[k], j + dirs[k + 1]];
                if (x >= 0 && x < m && y >= 0 && y < n && ans[x][y] == -1) {
                    tq.push([x, y]);
                    ans[x][y] = ans[i][j] + 1;
                }
            }
        }
        q = tq;
    }
    return ans;
}
