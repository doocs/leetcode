/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function (n) {
    const ans = new Array(n).fill(0).map(() => new Array(n).fill(0));
    let [i, j, k] = [0, 0, 0];
    const dirs = [
        [0, 1],
        [1, 0],
        [0, -1],
        [-1, 0],
    ];
    for (let v = 1; v <= n * n; ++v) {
        ans[i][j] = v;
        let [x, y] = [i + dirs[k][0], j + dirs[k][1]];
        if (x < 0 || y < 0 || x >= n || y >= n || ans[x][y] > 0) {
            k = (k + 1) % 4;
            [x, y] = [i + dirs[k][0], j + dirs[k][1]];
        }
        [i, j] = [x, y];
    }
    return ans;
};
