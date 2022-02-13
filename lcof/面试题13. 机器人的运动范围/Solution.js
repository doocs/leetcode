/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    const vis = new Array(m * n).fill(false);
    let dfs = function (i, j) {
        if (
            i >= m ||
            j >= n ||
            vis[i * n + j] ||
            (i % 10) + Math.floor(i / 10) + (j % 10) + Math.floor(j / 10) > k
        ) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
};
