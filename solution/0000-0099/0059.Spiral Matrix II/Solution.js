/**
 * @param {number} n
 * @return {number[][]}
 */
var generateMatrix = function (n) {
    const ans = Array.from({ length: n }, () => Array(n).fill(0));
    const dirs = [0, 1, 0, -1, 0];
    let [i, j, k] = [0, 0, 0];
    for (let v = 1; v <= n * n; v++) {
        ans[i][j] = v;
        const [x, y] = [i + dirs[k], j + dirs[k + 1]];
        if (x < 0 || x >= n || y < 0 || y >= n || ans[x][y] !== 0) {
            k = (k + 1) % 4;
        }
        i += dirs[k];
        j += dirs[k + 1];
    }
    return ans;
};
