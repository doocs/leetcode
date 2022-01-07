/**
 * @param {string} s
 * @return {number}
 */
var maxDepth = function (s) {
    let n = 0,
        ans = 0;
    for (let c of s) {
        if (c == '(') ans = Math.max(ans, ++n);
        else if (c == ')') --n;
    }
    return ans;
};
