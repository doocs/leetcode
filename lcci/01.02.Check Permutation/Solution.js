/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    if (s1.length !== s2.length) {
        return false;
    }
    const cnt = {};
    for (const c of s1) {
        cnt[c] = (cnt[c] || 0) + 1;
    }
    for (const c of s2) {
        if (!cnt[c]) {
            return false;
        }
        cnt[c]--;
    }
    return true;
};
