/**
 * @param {string} s
 * @param {string} t
 * @return {number}
 */
var longestCommonPrefix = function (s, t) {
    const [n, m] = [s.length, t.length];
    let [i, j] = [0, 0];
    let rem = false;
    while (i < n && j < m) {
        if (s[i] !== t[j]) {
            if (rem) {
                break;
            }
            rem = true;
        } else {
            ++j;
        }
        ++i;
    }
    return j;
};
