/**
 * @param {string} s
 * @return {boolean}
 */
var canPermutePalindrome = function (s) {
    let ss = new Set();
    for (let c of s) {
        if (ss.has(c)) {
            ss.delete(c);
        } else {
            ss.add(c);
        }
    }
    return ss.size < 2;
};
