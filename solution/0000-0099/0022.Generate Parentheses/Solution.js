/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function (n) {
    let ans = [];
    let dfs = function (left, right, t) {
        if (left == n && right == n) {
            ans.push(t);
            return;
        }
        if (left < n) {
            dfs(left + 1, right, t + '(');
        }
        if (right < left) {
            dfs(left, right + 1, t + ')');
        }
    };
    dfs(0, 0, '');
    return ans;
};
