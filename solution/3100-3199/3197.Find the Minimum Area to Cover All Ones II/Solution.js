/**
 * @param {number[][]} grid
 * @return {number}
 */
var minimumSum = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    let ans = m * n;
    const inf = Number.MAX_SAFE_INTEGER;
    const f = (i1, j1, i2, j2) => {
        let [x1, y1] = [inf, inf];
        let [x2, y2] = [-inf, -inf];
        for (let i = i1; i <= i2; i++) {
            for (let j = j1; j <= j2; j++) {
                if (grid[i][j] === 1) {
                    x1 = Math.min(x1, i);
                    y1 = Math.min(y1, j);
                    x2 = Math.max(x2, i);
                    y2 = Math.max(y2, j);
                }
            }
        }
        return x1 === inf ? 0 : (x2 - x1 + 1) * (y2 - y1 + 1);
    };

    for (let i1 = 0; i1 < m - 1; i1++) {
        for (let i2 = i1 + 1; i2 < m - 1; i2++) {
            ans = Math.min(
                ans,
                f(0, 0, i1, n - 1) + f(i1 + 1, 0, i2, n - 1) + f(i2 + 1, 0, m - 1, n - 1),
            );
        }
    }

    for (let j1 = 0; j1 < n - 1; j1++) {
        for (let j2 = j1 + 1; j2 < n - 1; j2++) {
            ans = Math.min(
                ans,
                f(0, 0, m - 1, j1) + f(0, j1 + 1, m - 1, j2) + f(0, j2 + 1, m - 1, n - 1),
            );
        }
    }

    for (let i = 0; i < m - 1; i++) {
        for (let j = 0; j < n - 1; j++) {
            ans = Math.min(ans, f(0, 0, i, j) + f(0, j + 1, i, n - 1) + f(i + 1, 0, m - 1, n - 1));
            ans = Math.min(
                ans,
                f(0, 0, i, n - 1) + f(i + 1, 0, m - 1, j) + f(i + 1, j + 1, m - 1, n - 1),
            );
            ans = Math.min(ans, f(0, 0, i, j) + f(i + 1, 0, m - 1, j) + f(0, j + 1, m - 1, n - 1));
            ans = Math.min(
                ans,
                f(0, 0, m - 1, j) + f(0, j + 1, i, n - 1) + f(i + 1, j + 1, m - 1, n - 1),
            );
        }
    }

    return ans;
};
