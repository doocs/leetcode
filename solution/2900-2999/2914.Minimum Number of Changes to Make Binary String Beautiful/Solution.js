/**
 * @param {string} s
 * @return {number}
 */
var minChanges = function (s) {
    let ans = 0;
    for (let i = 1; i < s.length; i += 2) {
        if (s[i] !== s[i - 1]) {
            ++ans;
        }
    }
    return ans;
};
