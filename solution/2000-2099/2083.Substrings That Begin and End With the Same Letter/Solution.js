/**
 * @param {string} s
 * @return {number}
 */
var numberOfSubstrings = function (s) {
    const cnt = {};
    let ans = 0;
    for (const c of s) {
        cnt[c] = (cnt[c] || 0) + 1;
        ans += cnt[c];
    }
    return ans;
};
