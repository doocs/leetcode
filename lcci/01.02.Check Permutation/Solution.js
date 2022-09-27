/**
 * @param {string} s1
 * @param {string} s2
 * @return {boolean}
 */
var CheckPermutation = function (s1, s2) {
    if (s1.length != s2.length) {
        return false;
    }
    const cnt = new Array(26).fill(0);
    for (let i = 0; i < s1.length; ++i) {
        const j = s1.codePointAt(i) - 'a'.codePointAt(0);
        ++cnt[j];
    }
    for (let i = 0; i < s2.length; ++i) {
        const j = s2.codePointAt(i) - 'a'.codePointAt(0);
        if (--cnt[j] < 0) {
            return false;
        }
    }
    return true;
};
