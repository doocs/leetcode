/**
 * @param {string} s
 * @return {number}
 */
var maxDepth = function (s) {
    let ans = 0;
    let d = 0;
    for (const c of s) {
        if (c === '(') {
            ans = Math.max(ans, ++d);
        } else if (c === ')') {
            --d;
        }
    }
    return ans;
};
