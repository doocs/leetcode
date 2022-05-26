/**
 * @param {number[]} postorder
 * @return {boolean}
 */
var verifyPostorder = function (postorder) {
    if (postorder.length < 2) return true;
    function dfs(i, n) {
        if (n <= 0) return true;
        const v = postorder[i + n - 1];
        let j = i;
        while (j < i + n && postorder[j] < v) ++j;
        for (let k = j; k < i + n; ++k) {
            if (postorder[k] < v) {
                return false;
            }
        }
        return dfs(i, j - i) && dfs(j, n + i - j - 1);
    }
    return dfs(0, postorder.length);
};
