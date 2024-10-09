/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var minSteps = function (s, t) {
    const cnt = Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt(0) - 97];
    }
    let ans = 0;
    for (const c of t) {
        if (--cnt[c.charCodeAt(0) - 97] < 0) {
            ++ans;
        }
    }
    return ans;
};
