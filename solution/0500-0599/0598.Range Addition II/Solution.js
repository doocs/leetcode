/**
 * @param {number} m
 * @param {number} n
 * @param {number[][]} ops
 * @return {number}
 */
var maxCount = function (m, n, ops) {
    for (const [a, b] of ops) {
        m = Math.min(m, a);
        n = Math.min(n, b);
    }
    return m * n;
};
