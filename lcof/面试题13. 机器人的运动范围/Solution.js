/**
 * @param {number} m
 * @param {number} n
 * @param {number} k
 * @return {number}
 */
var movingCount = function (m, n, k) {
    const vis = Array(m * n).fill(false);
    const f = x => {
        return ((x / 10) | 0) + (x % 10);
    };
    const dfs = (i, j) => {
        if (i >= m || j >= n || vis[i * n + j] || f(i) + f(j) > k) {
            return 0;
        }
        vis[i * n + j] = true;
        return 1 + dfs(i + 1, j) + dfs(i, j + 1);
    };
    return dfs(0, 0);
};
