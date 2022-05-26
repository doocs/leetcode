/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    let r = 1;
    let tmp = x;
    let tag = 0;
    if (n < 0) {
        tag = 1;
        n = -n;
    }
    while (n) {
        if (n & 1) {
            r *= tmp;
        }
        tmp *= tmp;
        n >>>= 1;
    }
    return tag ? 1 / r : r;
};
