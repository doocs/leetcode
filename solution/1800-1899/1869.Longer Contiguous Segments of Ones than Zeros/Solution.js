/**
 * @param {string} s
 * @return {boolean}
 */
var checkZeroOnes = function (s) {
    let max0 = 0,
        max1 = 0;
    let t0 = 0,
        t1 = 0;
    for (let char of s) {
        if (char == '0') {
            t0++;
            t1 = 0;
        } else {
            t1++;
            t0 = 0;
        }
        max0 = Math.max(max0, t0);
        max1 = Math.max(max1, t1);
    }
    return max1 > max0;
};
