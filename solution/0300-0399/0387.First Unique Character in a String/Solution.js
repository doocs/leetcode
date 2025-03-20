/**
 * @param {string} s
 * @return {number}
 */
var firstUniqChar = function (s) {
    const cnt = new Map();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    for (let i = 0; i < s.length; ++i) {
        if (cnt.get(s[i]) === 1) {
            return i;
        }
    }
    return -1;
};
