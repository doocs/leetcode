/**
 * @param {string} s
 * @return {number}
 */
var balancedStringSplit = function (s) {
    let ans = 0;
    let l = 0;
    for (let c of s) {
        if (c == 'L') {
            ++l;
        } else {
            --l;
        }
        if (l == 0) {
            ++ans;
        }
    }
    return ans;
};
