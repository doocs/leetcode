/**
 * @param {number[][]} grid
 * @param {number} stampHeight
 * @param {number} stampWidth
 * @return {boolean}
 */
var possibleToStamp = function (grid, stampHeight, stampWidth) {
    const m = grid.length;
    const n = grid[0].length;
    let s = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let d = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    let cnt = new Array(m + 1).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            s[i + 1][j + 1] = s[i + 1][j] + s[i][j + 1] - s[i][j] + grid[i][j];
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] == 0) {
                let [x, y] = [i + stampHeight, j + stampWidth];
                if (
                    x <= m &&
                    y <= n &&
                    s[x][y] - s[i][y] - s[x][j] + s[i][j] == 0
                ) {
                    d[i][j]++;
                    d[i][y]--;
                    d[x][j]--;
                    d[x][y]++;
                }
            }
        }
    }
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            cnt[i + 1][j + 1] =
                cnt[i + 1][j] + cnt[i][j + 1] - cnt[i][j] + d[i][j];
            if (grid[i][j] == 0 && cnt[i + 1][j + 1] == 0) {
                return false;
            }
        }
    }
    return true;
};
