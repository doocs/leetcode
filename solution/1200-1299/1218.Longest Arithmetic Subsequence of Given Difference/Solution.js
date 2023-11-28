/**
 * @param {number[]} arr
 * @param {number} difference
 * @return {number}
 */
var longestSubsequence = function (arr, difference) {
    const f = new Map();
    for (const x of arr) {
        f.set(x, (f.get(x - difference) || 0) + 1);
    }
    return Math.max(...f.values());
};
