/**
 * @param {string} s
 * @return {number}
 */
var firstUniqChar = function (s) {
    const counter = new Map();
    for (let c of s) {
        counter[c] = (counter[c] || 0) + 1;
    }
    for (let i = 0; i < s.length; ++i) {
        if (counter[s[i]] == 1) {
            return i;
        }
    }
    return -1;
};
