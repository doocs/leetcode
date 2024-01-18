/**
 * @param {number[][]} grid
 * @param {number} stampHeight
 * @param {number} stampWidth
 * @return {boolean}
 */
var possibleToStamp = function (grid, stampHeight, stampWidth) {
    const m = grid.length;
    const n = grid[0].length;
    const s = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            s[i][j] = s[i - 1][j] + s[i][j - 1] - s[i - 1][j - 1] + grid[i - 1][j - 1];
        }
    }

    const d = Array.from({ length: m + 2 }, () => Array(n + 2).fill(0));
    for (let i = 1; i + stampHeight - 1 <= m; ++i) {
        for (let j = 1; j + stampWidth - 1 <= n; ++j) {
            const [x, y] = [i + stampHeight - 1, j + stampWidth - 1];
            if (s[x][y] - s[x][j - 1] - s[i - 1][y] + s[i - 1][j - 1] === 0) {
                d[i][j]++;
                d[i][y + 1]--;
                d[x + 1][j]--;
                d[x + 1][y + 1]++;
            }
        }
    }

    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            d[i][j] += d[i - 1][j] + d[i][j - 1] - d[i - 1][j - 1];
            if (grid[i - 1][j - 1] === 0 && d[i][j] === 0) {
                return false;
            }
        }
    }
    return true;
};
