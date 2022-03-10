/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    let res = [];
    dfs(n, 0, 0, '', res);
    return res;
};

function dfs(n, left, right, prev, res) {
    if (left == n && right == n) {
        res.push(prev);
        return;
    }
    if (left < n) {
        dfs(n, left + 1, right, prev + '(', res);
    }
    if (right < left) {
        dfs(n, left, right + 1, prev + ')', res);
    }
}
