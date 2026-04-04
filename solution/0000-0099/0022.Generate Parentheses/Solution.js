/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    const dfs = (l, r, t) => {
        if (l > n || r > n || l < r) {
            return;
        }
        if (l == n && r == n) {
            ans.push(t);
            return;
        }
        dfs(l + 1, r, t + '(');
        dfs(l, r + 1, t + ')');
    };
    const ans = [];
    dfs(0, 0, '');
    return ans;
};
