/**
 * @param {string} s
 * @return {boolean}
 */
var canPermutePalindrome = function (s) {
    const cnt = new Map();
    for (const c of s) {
        cnt.set(c, (cnt.get(c) || 0) + 1);
    }
    return [...cnt.values()].filter(v => v % 2 === 1).length < 2;
};
