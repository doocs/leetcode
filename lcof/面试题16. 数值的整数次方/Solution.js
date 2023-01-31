/**
 * @param {number} x
 * @param {number} n
 * @return {number}
 */
var myPow = function (x, n) {
    return n >= 0 ? qmi(x, n) : 1 / qmi(x, -n);
};

function qmi(a, k) {
    let res = 1;
    while (k) {
        if (k & 1) {
            res *= a;
        }
        a *= a;
        k >>>= 1;
    }
    return res;
}
