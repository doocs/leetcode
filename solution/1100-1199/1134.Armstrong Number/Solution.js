/**
 * @param {number} n
 * @return {boolean}
 */
var isArmstrong = function (n) {
    const k = String(n).length;
    let s = 0;
    let t = n;
    while (t) {
        s += Math.pow(t % 10, k);
        t = Math.floor(t / 10);
    }
    return n == s;
};
