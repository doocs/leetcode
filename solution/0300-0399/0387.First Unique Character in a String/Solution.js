/**
 * @param {string} s
 * @return {number}
 */
var firstUniqChar = function (s) {
    const cnt = new Array(26).fill(0);
    for (const c of s) {
        ++cnt[c.charCodeAt() - 'a'.charCodeAt()];
    }
    for (let i = 0; i < s.length; ++i) {
        if (cnt[s[i].charCodeAt() - 'a'.charCodeAt()] === 1) {
            return i;
        }
    }
    return -1;
};
