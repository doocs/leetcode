/**
 * @param {string} s
 * @param {string} p
 * @return {boolean}
 */
var isMatch = function (s, p) {
    const m = s.length;
    const n = p.length;
    const f = Array.from({ length: m + 1 }, () => Array(n + 1).fill(0));
    const dfs = (i, j) => {
        if (j >= n) {
            return i == m;
        }
        if (f[i][j]) {
            return f[i][j] == 1;
        }
        let res = -1;
        if (j + 1 < n && p[j + 1] === '*') {
            if (dfs(i, j + 2) || (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j))) {
                res = 1;
            }
        } else if (i < m && (s[i] == p[j] || p[j] == '.') && dfs(i + 1, j + 1)) {
            res = 1;
        }
        f[i][j] = res;
        return res == 1;
    };
    return dfs(0, 0);
};
